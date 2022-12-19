package managers;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

import model.Bola;
import model.Bullet;

public class BolasManager {

    private final ArrayList<Bola> listBullet = new ArrayList<Bola>();
    private final ArrayList<Bola> listBulletDead = new ArrayList<Bola>();
    private final ArrayList<Bola> listBulletEnemy = new ArrayList<Bola>();
    private final ArrayList<Bola> listBulletDeadEnemy = new ArrayList<Bola>();
    private static BolasManager singleton;
    Stage stage;

    public static BolasManager getSingleton(){
        if (singleton == null) {
            singleton = new BolasManager();
        }
        return singleton;
    }

    public Bola shootCreate(float positionX, float positionY, boolean ally) {
        Bola nuevo;
        if(ally){
            saveShoot(positionX, positionY);
            nuevo = listBullet.get(listBullet.size() - 1);
            if (listBullet.get(0).getY() > SettingManager.SCREEN_HEIGHT) {
                deadShoot(listBullet.get(0), 0);
            }
        }else{
            saveShootEnemy(positionX, positionY);
            nuevo = listBulletEnemy.get(listBulletEnemy.size() - 1);
            if (listBulletEnemy.get(0).getY() < 0) {
                deadShootEnemy(listBulletEnemy.get(0), 0);
            }
        }

        return nuevo;
    }

    public ArrayList<Bola> getListBullet() {
        return listBullet;
    }
    public ArrayList<Bola> getListBulletEnemy() {
        return listBulletEnemy;
    }

    public void deadShoot(Bola shoot, int i) {
        shoot.setY(1000);
        listBulletDead.add(shoot);
        listBullet.remove(i);
    }
    public void deadShootEnemy(Bola shoot, int i) {
        shoot.setY(-10);
        listBulletDeadEnemy.add(shoot);
        listBulletEnemy.remove(i);
    }

    public void saveShoot(float positionX, float positionY) {
        if (listBulletDead.isEmpty()) {
            listBullet.add(new Bola(stage,positionX,positionY));
            listBullet.get(listBullet.size() - 1).setX(positionX);
            listBullet.get(listBullet.size() - 1).setY(positionY);
        } else {
            listBullet.add(listBulletDead.get(0));
            listBulletDead.remove(0);
            listBullet.get(listBullet.size() - 1).setX(positionX);
            listBullet.get(listBullet.size() - 1).setY(positionY);
        }
    }
    public void saveShootEnemy(float positionX, float positionY) {
        if (listBulletDeadEnemy.isEmpty()) {
            listBulletEnemy.add(new Bola(stage,positionX,positionY));
            listBulletEnemy.get(listBulletEnemy.size() - 1).setX(positionX);
            listBulletEnemy.get(listBulletEnemy.size() - 1).setY(positionY);
        } else {
            listBulletEnemy.add(listBulletDeadEnemy.get(0));
            listBulletDeadEnemy.remove(0);
            listBulletEnemy.get(listBulletEnemy.size() - 1).setX(positionX);
            listBulletEnemy.get(listBulletEnemy.size() - 1).setY(positionY);
        }
    }
}
