package Entities;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class UserProfile {

    public Map<String, String> getUserProfile(String role) {
        Map<String, String> infoMap = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM UserProfileEntity WHERE role = :role";
            Query<UserProfileEntity> query = session.createQuery(hql, UserProfileEntity.class);
            query.setParameter("role", role);
            UserProfileEntity userProfile = query.uniqueResult();

            if (userProfile != null) {
                infoMap = new HashMap<>();
                infoMap.put("role", userProfile.getRole());
                infoMap.put("description", userProfile.getDescription());
                infoMap.put("isSuspended", String.valueOf(userProfile.isSuspended()));
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return infoMap;
    }

    public Vector<String> getRoles() {
        Vector<String> roles = new Vector<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "SELECT role FROM UserProfileEntity";
            Query<String> query = session.createQuery(hql, String.class);
            List<String> resultList = query.list();

            roles.addAll(resultList);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return roles;
    }

    public boolean insertToDB(String role, String description, boolean isSuspended) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            UserProfileEntity userProfile = new UserProfileEntity(role, description, isSuspended);
            session.save(userProfile);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDB(String role, String updatedDesc) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "UPDATE UserProfileEntity SET description = :description WHERE role = :role";
            Query<?> query = session.createQuery(hql);
            query.setParameter("description", updatedDesc);
            query.setParameter("role", role);
            int updatedRows = query.executeUpdate();
            transaction.commit();
            return updatedRows > 0;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean suspendUserProfile(String role) {
        return updateSuspensionStatus(role, true);
    }

    public boolean reinstateUserProfile(String role) {
        return updateSuspensionStatus(role, false);
    }

    private boolean updateSuspensionStatus(String role, boolean isSuspended) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "UPDATE UserProfileEntity SET isSuspended = :isSuspended WHERE role = :role";
            Query<?> query = session.createQuery(hql);
            query.setParameter("isSuspended", isSuspended);
            query.setParameter("role", role);
            int updatedRows = query.executeUpdate();
            transaction.commit();
            return updatedRows > 0;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
