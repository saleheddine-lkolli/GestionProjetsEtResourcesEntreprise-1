package Service;

import DAO.DAOPC;
import DAO.Entities.PC;

import java.util.List;

public class IGestiondesRessources implements GestiondesRessources {
    private DAOPC daopc ;
    public IGestiondesRessources(DAOPC daopc){
        this.daopc =daopc;
    }

    @Override
    public List<PC> getListePCs() {
        return daopc.findallNomUse();
    }

    @Override
    public PC getPC(int id) {
        return daopc.finById(id);
    }

    @Override
    public PC getPC(String nom) {
        return daopc.findbyName(nom);
    }

    @Override
    public void SupprimerPC(PC pc) {
        daopc.detete(pc);
    }

    @Override
    public void AjouterPC(PC pc) {
        daopc.save(pc);
    }

    @Override
    public void ModifierPC(PC pc) {
        daopc.update(pc);
    }
}
