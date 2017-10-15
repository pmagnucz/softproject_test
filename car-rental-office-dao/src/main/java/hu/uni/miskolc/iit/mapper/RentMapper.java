package hu.uni.miskolc.iit.mapper;

import hu.uni.miskolc.iit.entity.RentEntity;
import hu.uni.miskolc.iit.model.Rent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zsoltme on 2017.10.15..
 */
public class RentMapper {
    public static RentEntity mapModelToEntity(Rent rent){
        //converting format for Date to String
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        RentEntity rentEntity = new RentEntity();

        rentEntity.setId(Long.valueOf(rent.getId()));
        rentEntity.setCustomerId(String.valueOf(rent.getCustomerId()));
        rentEntity.setCompanyId(String.valueOf(rent.getCompanyId()));
        rentEntity.setVehicleId(String.valueOf(rent.getVehicleId()));
        rentEntity.setStartDate(format.format(rent.getStartDate()));
        rentEntity.setEndDate(format.format(rent.getEndDate()));
        rentEntity.setDurationExtendable(String.valueOf(rent.isDurationExtendable()));
        rentEntity.setExtendedHours(String.valueOf(rent.getExtendedHours()));
        rentEntity.setKmUsed(String.valueOf(rent.getKmUsed()));
        rentEntity.setDayFee(String.valueOf(rent.getDayFee()));
        rentEntity.setKmFee(String.valueOf(rent.getKmFee()));
        rentEntity.setOtherFee(String.valueOf(rent.getOtherFee()));
        rentEntity.setTotalFee(String.valueOf(rent.getTotalFee()));
        rentEntity.setPaid(String.valueOf(rent.isPaid()));

        return rentEntity;
    }

    public static List<RentEntity> mapRentListToRentEntityList(List<Rent> rentList){
        List<RentEntity> rentEntityList = new ArrayList<>(rentList.size());
        for (Rent rent : rentList)
        {
            rentEntityList.add(mapModelToEntity(rent));
        }
        return rentEntityList;
    }

    public static Rent mapEntityToModel(RentEntity rentEntity){
        Rent rent = new Rent();

        //converting format for String to Date
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse(rentEntity.getStartDate());
            endDate = format.parse(rentEntity.getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent.setId(Math.toIntExact(rentEntity.getId()));
        rent.setCustomerId(Integer.parseInt(rentEntity.getCustomerId()));
        rent.setCompanyId(Integer.parseInt(rentEntity.getCompanyId()));
        rent.setVehicleId(Integer.parseInt(rentEntity.getVehicleId()));
        rent.setStartDate(startDate);
        rent.setEndDate(endDate);
        rent.setDurationExtendable(Boolean.valueOf(rentEntity.getDurationExtendable()));
        rent.setExtendedHours(Integer.parseInt(rentEntity.getExtendedHours()));
        rent.setKmUsed(Integer.parseInt(rentEntity.getKmUsed()));
        rent.setDayFee(Double.parseDouble(rentEntity.getDayFee()));
        rent.setKmFee(Double.parseDouble(rentEntity.getKmFee()));
        rent.setOtherFee(Double.parseDouble(rentEntity.getOtherFee()));
        rent.setTotalFee(Double.parseDouble(rentEntity.getTotalFee()));
        rent.setPaid(Boolean.valueOf(rentEntity.getPaid()));

        return rent;
    }

    public static List<Rent> mapRentEntityListToModelList(List<RentEntity> rentEntityList){
        List<Rent> rentList = new ArrayList<>(rentEntityList.size());
        for (RentEntity rentEntity : rentEntityList) {
            rentList.add(mapEntityToModel(rentEntity));
        }
        return rentList;
    }
}
