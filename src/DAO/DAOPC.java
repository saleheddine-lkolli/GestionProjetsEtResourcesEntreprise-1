package DAO;

import DAO.Entities.PC;

import java.util.List;

public interface DAOPC extends DAO<PC>{
    public PC findbyName(String name );
    public List<PC> findallNomUse();
}
