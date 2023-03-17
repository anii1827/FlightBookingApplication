
package com.Userapp.Users.Util;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import com.Userapp.Users.DTO.BookingDTO;
import com.Userapp.Users.DTO.PassangerDTO;
import com.Userapp.Users.DTO.UserDTO;
import com.Userapp.Users.Exception.CustomException;
import com.Userapp.Users.Model.Booking;
import com.Userapp.Users.Model.Passanger;
import com.Userapp.Users.Model.User;

public class Convert {
	
		public static User DtoToEntity(UserDTO userDTO) {
				try {
					User user = new User();
					BeanUtils.copyProperties(userDTO, user);
					return user;
				}
				catch(Exception e) {
					throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}
		
		public static Passanger DtoToEntity(PassangerDTO passangerDTO) {
			try {
				Passanger passanger = new Passanger();
				BeanUtils.copyProperties(passangerDTO, passanger);
				return passanger;
			}
			catch(Exception e) {
				throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		public static PassangerDTO DtoToEntity(Passanger passanger) {
			try {
				PassangerDTO pass = new PassangerDTO();
				BeanUtils.copyProperties(passanger, pass);
				return pass;
			}
			catch(Exception e) {
				throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		public static BookingDTO DtoToEntity(Booking booking) {
			Set<Passanger> passanger = booking.getPassanger();
			
			Set<PassangerDTO> collect = passanger.stream().map((x)->{
				PassangerDTO dtoToEntity = DtoToEntity(x);
				return dtoToEntity;
			}).collect(Collectors.toSet());
			
			try {
				booking.getPassanger().stream().map((x)->{
				x.setBooking(null);
				return x;
			}).collect(Collectors.toSet());
			BookingDTO dto = new BookingDTO();
			BeanUtils.copyProperties(booking, dto);
			dto.setPassanger(collect);
			return dto;
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}

}
