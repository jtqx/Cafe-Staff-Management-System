package Entities;

import jakarta.persistence.*;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Entity
@Table(name = "useraccount")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "isSuspended")
    private boolean isSuspended;

    // Constructors
    public UserAccount() {}

    public UserAccount(String username, String password, String role, boolean isSuspended) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isSuspended = isSuspended;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    // CRUD Operations

    public static UserAccount getUserAccountByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM UserAccount WHERE username = :username", UserAccount.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    public static List<UserAccount> getAllUserAccounts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM UserAccount", UserAccount.class).list();
        } finally {
            session.close();
        }
    }

    public static boolean saveUserAccount(UserAccount userAccount) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(userAccount);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateUserAccount(UserAccount userAccount) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(userAccount);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteUserAccount(UserAccount userAccount) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(userAccount);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
