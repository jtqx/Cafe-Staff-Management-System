package Entities;

// Hibernate imports
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

// Java imports
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "workslot")
public class WorkSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private String date;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "endTime")
    private String endTime;

    @Column(name = "maxCashier")
    private int maxCashier;

    @Column(name = "maxChef")
    private int maxChef;

    @Column(name = "maxWaiter")
    private int maxWaiter;

    @Column(name = "availabilityStatus")
    private String availabilityStatus;

    // Getters and Setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getMaxCashier() {
        return maxCashier;
    }

    public void setMaxCashier(int maxCashier) {
        this.maxCashier = maxCashier;
    }

    public int getMaxChef() {
        return maxChef;
    }

    public void setMaxChef(int maxChef) {
        this.maxChef = maxChef;
    }

    public int getMaxWaiter() {
        return maxWaiter;
    }

    public void setMaxWaiter(int maxWaiter) {
        this.maxWaiter = maxWaiter;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    // Hibernate methods

    public Map<String, String> getWorkSlot(String date, String startTime, String endTime, String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM WorkSlot WHERE date = :date AND startTime = :startTime AND endTime = :endTime";
            if (!status.isEmpty()) {
                hql += " AND availabilityStatus = :status";
            }

            var query = session.createQuery(hql, WorkSlot.class);
            query.setParameter("date", date);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            if (!status.isEmpty()) {
                query.setParameter("status", status);
            }

            WorkSlot slot = query.uniqueResult();
            if (slot != null) {
                Map<String, String> infoMap = new HashMap<>();
                infoMap.put("date", slot.getDate());
                infoMap.put("startTime", slot.getStartTime());
                infoMap.put("endTime", slot.getEndTime());
                infoMap.put("maxCashier", String.valueOf(slot.getMaxCashier()));
                infoMap.put("maxChef", String.valueOf(slot.getMaxChef()));
                infoMap.put("maxWaiter", String.valueOf(slot.getMaxWaiter()));
                infoMap.put("availabilityStatus", slot.getAvailabilityStatus());
                return infoMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<WorkSlot> getManySlots(String date, String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM WorkSlot";

            if (!date.isEmpty() && !status.isEmpty()) {
                hql += " WHERE date = :date AND availabilityStatus = :status";
            } else if (!date.isEmpty()) {
                hql += " WHERE date = :date";
            } else if (!status.isEmpty()) {
                hql += " WHERE availabilityStatus = :status";
            }

            var query = session.createQuery(hql, WorkSlot.class);

            if (!date.isEmpty()) {
                query.setParameter("date", date);
            }

            if (!status.isEmpty()) {
                query.setParameter("status", status);
            }

            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveOrUpdateWorkSlot() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(this);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteWorkSlot() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(this);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
}
