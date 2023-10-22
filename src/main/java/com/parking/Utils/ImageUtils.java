package com.parking.utils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.parking.entity.ParkingLot;
import com.parking.entity.ParkingLotImage;

public class ImageUtils {
	 public static byte[] compressImage(byte[] data) {
	        Deflater deflater = new Deflater();
	        deflater.setLevel(Deflater.BEST_COMPRESSION);
	        deflater.setInput(data);
	        deflater.finish();

	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	        byte[] tmp = new byte[4*1024];
	        while (!deflater.finished()) {
	            int size = deflater.deflate(tmp);
	            outputStream.write(tmp, 0, size);
	        }
	        try {
	            outputStream.close();
	        } catch (Exception ignored) {
	        }
	        return outputStream.toByteArray();
	    }



	    public static byte[] decompressImage(byte[] data) {
	        Inflater inflater = new Inflater();
	        inflater.setInput(data);
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	        byte[] tmp = new byte[4*1024];
	        try {
	            while (!inflater.finished()) {
	                int count = inflater.inflate(tmp);
	                outputStream.write(tmp, 0, count);
	            }
	            outputStream.close();
	        } catch (Exception ignored) {
	        }
	        return outputStream.toByteArray();
	    }
	    
	    public static void decompressImage(List<ParkingLotImage> images )
	    {
	    	for(ParkingLotImage image : images)
	    	{
	    		image.setData(decompressImage(image.getData()));
	    	}
	    }
}
