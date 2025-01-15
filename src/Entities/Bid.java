package Entities;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

@Entity
@Table(name = "bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "userId")
    private int userId;

    @Column(name = "date")
    private String date;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "endTime")
    private String endTime;

    @Column(name = "cafeRole")
    private String cafeRole;

    @Column(name = "status")
    private String status;

    // Constructors
    public Bid() {
    }

    public Bid(int userId, String date, String startTime, String endTime, String cafeRole, String status) {
        this.userId = userId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cafeRole = cafeRole;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getCafeRole() {
        return cafeRole;
    }

    public void setCafeRole(String cafeRole) {
        this.cafeRole = cafeRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // CRUD operations

    public static Bid getBidById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Bid.class, id);
        }
    }

    public static List<Bid> getAllBids() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Bid", Bid.class).list();
        }
    }

    public static boolean saveBid(Bid bid) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(bid);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateBid(Bid bid) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(bid);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteBid(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Bid bid = session.get(Bid.class, id);
            if (bid != null) {
                session.delete(bid);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
