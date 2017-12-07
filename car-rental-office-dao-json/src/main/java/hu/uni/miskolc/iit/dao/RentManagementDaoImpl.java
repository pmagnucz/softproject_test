package hu.uni.miskolc.iit.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.uni.miskolc.iit.dao.beans.RentDaoBean;
import hu.uni.miskolc.iit.model.Rent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by pmagnucz on 2017. 12. 03..
 */
public class RentManagementDaoImpl implements RentManagementDao {
    private File database;

    public RentManagementDaoImpl(File file)
    {
        this.database = file;
    }

    @Override
    public Rent createRent(Rent rent) {
        boolean exist = false;
        List<Rent> rents = readDatabase();
        if (rents.size() != 0){
            for (Rent r : rents)
            {
                if (r.getId() == rent.getId())
                {
                    exist = true;
                    r.setCustomerId(rent.getCustomerId());
                    r.setCompanyId(rent.getCompanyId());
                    r.setVehicleId(rent.getVehicleId());
                    r.setStartDate(rent.getStartDate());
                    r.setEndDate(rent.getEndDate());
                    r.setDurationExtendable(rent.isDurationExtendable());
                    r.setExtendedHours(rent.getExtendedHours());
                    r.setKmUsed(rent.getKmUsed());
                    r.setDayFee(rent.getDayFee());
                    r.setKmFee(rent.getKmFee());
                    r.setOtherFee(rent.getOtherFee());
                    r.setTotalFee(rent.getTotalFee());
                    r.setPaid(rent.isPaid());
                }
            }
        }
        if (!exist)
        {
            rents.add(rent);
        }

        writeDatabase(rents);
        return rent;
    }

    @Override
    public Rent getRentById(Long id) {
        List<Rent> rents = readDatabase();
        for (Rent rent : rents)
        {
            if (rent.getId().equals(id))
            {
                return rent;
            }
        }
        return null;
    }

    @Override
    public Collection<Rent> getRents() {
        return readDatabase();
    }

    @Override
    public void deleteRent(Rent rent) {
        List<Rent> rents = readDatabase();
        rents.remove(rent);
        writeDatabase(rents);
    }

    @Override
    public boolean exists(Rent rent) {
        List<Rent> rents = readDatabase();
        for (Rent item : rents)
        {
            if (item.getId().equals(rent.getId()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        writeDatabase(new ArrayList<Rent>());
    }

    private List<Rent> readDatabase(){
        List<Rent> result = new ArrayList<Rent>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<RentDaoBean> beans = Arrays.asList(mapper.readValue(database, RentDaoBean[].class));
            for (RentDaoBean bean : beans)
            {
                result.add(bean.extract());
            }
        } catch (IOException e) {
            System.out.println(database.getAbsolutePath());
            e.printStackTrace();
        }
        return result;
    }

    private void writeDatabase(List<Rent> rents)
    {
        List<RentDaoBean> beans = new ArrayList<RentDaoBean>();
        for (Rent rent : rents){
            beans.add(convert(rent));
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(database, beans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RentDaoBean convert(Rent rent)
    {
        RentDaoBean bean = new RentDaoBean();
        bean.setId(rent.getId());
        bean.setCustomerId(rent.getCustomerId());
        bean.setCompanyId(rent.getCompanyId());
        bean.setVehicleId(rent.getVehicleId());
        bean.setStartDate(rent.getStartDate());
        bean.setEndDate(rent.getEndDate());
        bean.setDurationExtendable(rent.isDurationExtendable());
        bean.setExtendedHours(rent.getExtendedHours());
        bean.setKmUsed(rent.getKmUsed());
        bean.setDayFee(rent.getDayFee());
        bean.setKmFee(rent.getKmFee());
        bean.setOtherFee(rent.getOtherFee());
        bean.setTotalFee(rent.getTotalFee());
        bean.setPaid(rent.isPaid());

        return bean;
    }
}
