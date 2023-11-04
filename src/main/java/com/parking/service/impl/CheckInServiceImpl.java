package com.parking.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.constant.TicketConstant;
import com.parking.dto.checkInOut.CheckInData;
import com.parking.dto.checkInOut.CheckInInformation;
import com.parking.entity.ParkingLot;
import com.parking.entity.Ticket;
import com.parking.entity.User;
import com.parking.model.ResponseObject;
import com.parking.model.VehicleData;
import com.parking.repository.ParkingLotRepository;
import com.parking.repository.PendingTicketRepository;
import com.parking.repository.TicketRepository;
import com.parking.repository.UserRepository;
import com.parking.service.CheckInService;
import com.parking.service.SocketService;
import com.parking.utils.DelayManager;

@Service
public class CheckInServiceImpl implements CheckInService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ParkingLotRepository parkingLotRepository;

	@Autowired
	private PendingTicketRepository pendingTicketRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private SocketService socketService;

	@Override
	@Transactional
	public ResponseObject checkIn(CheckInData data) {
		Optional<User> user = userRepository.findById(data.getUserId());
		Optional<ParkingLot> parkingLot = parkingLotRepository.findById(data.getParkingLotId());
		if (user.isEmpty() && parkingLot.isEmpty()) {
			return new ResponseObject(HttpStatus.NOT_FOUND, "thông tin người dùng hoặc bãi đỗ xe không hợp lệ.", null);
		}
		if (parkingLot.get().getNumberSlotRemaining() == 0) {
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Bãi đỗ xe đã hết chỗ.", null);
		}
		if (!pendingTicketRepository.addPendingTicket(data)) {
			return new ResponseObject(HttpStatus.FOUND, "Vé đã được tạo rồi.", null);
		}

		socketService.requestToEnterLicensePlate(data);
		VehicleData vehicleData = getVehicleData(data);
		
		if (ObjectUtils.isEmpty(vehicleData)) {
			pendingTicketRepository.removePendingTicket(data);
			return new ResponseObject(HttpStatus.FOUND, "Đã quá thời hạn nhập biển số. Hãy quét lại mã", null);
		}
		
		Ticket ticketDB = ticketRepository.findByUserIdAndParkingLotIdAndLicensePlate(data.getUserId(),data.getParkingLotId(), vehicleData.getLicensePlate());
		
		if (ObjectUtils.isEmpty(ticketDB)) {
			Instant now = Instant.now();
			ticketRepository.insert(now, vehicleData, data);
			parkingLotRepository.decreaseNumberSlotRemainingBy1(data.getParkingLotId());
			return new ResponseObject(HttpStatus.OK, "Tạo vé thành công", null);
		} else
			return new ResponseObject(HttpStatus.FOUND,
					"Người dùng đã có vé cho chiếc xe này trong hệ thống mà chưa thanh toán", null);

	}

	private VehicleData getVehicleData(CheckInData data) {
		Instant begin = Instant.now();
		while (pendingTicketRepository.isPendingTicket(data)) {

			Instant now = Instant.now();
			Duration duration = Duration.between(begin, now);
			if (duration.toSeconds() > TicketConstant.PENDING_TIME) {
				pendingTicketRepository.removePendingTicket(data);
				return null;
			}
			DelayManager.delay(1);
		}
		pendingTicketRepository.show();
		VehicleData vehicleData = pendingTicketRepository.getPendingTicketInformation(data);
		pendingTicketRepository.removePendingTicket(data);
		return vehicleData;
	}

	@Override
	public ResponseObject submitLicensePlate(CheckInInformation information) {
		Ticket ticketDB = ticketRepository.findByUserIdAndParkingLotIdAndLicensePlate(information.getCheckInData().getUserId(),
				information.getCheckInData().getParkingLotId(), information.getVehicleData().getLicensePlate());
		if (ObjectUtils.isEmpty(ticketDB)) {
			pendingTicketRepository.setPendingTicketInformation(information.getCheckInData(),
					information.getVehicleData());
			pendingTicketRepository.show();
		} else {
			pendingTicketRepository.removePendingTicket(information.getCheckInData());
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Vé đã được thêm trước đó, hãy thanh toán trước khi gửi xe", null);
		}
		return new ResponseObject(HttpStatus.OK, "Thêm thông tin thành công", null);
	}

}
