package Service;

import DAO.Entities.PC;

import java.util.List;

public interface GestiondesRessources {
    public List<PC> getListePCs();
    public PC getPC(int id);
    public PC getPC(String nom);
    public void SupprimerPC(PC pc);
    public void AjouterPC(PC pc);
    public void ModifierPC(PC pc);
}
