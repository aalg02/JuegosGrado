package managers;

import java.util.ArrayList;

import model.Bullet;

public class ShootManager {
    private final ArrayList<Bullet> listBullet = new ArrayList<Bullet>();
    private final ArrayList<Bullet> listBulletDead = new ArrayList<Bullet>();
    private final ArrayList<Bullet> listBulletEnemy = new ArrayList<Bullet>();
    private final ArrayList<Bullet> listBulletDeadEnemy = new ArrayList<Bullet>();
    private static ShootManager singleton;

    public static ShootManager getSingleton() {
        if (singleton == null) {
            singleton = new ShootManager();
        }
        return singleton;
    }

    public Bullet shootCreate(float positionX, float positionY, boolean ally) {
        Bullet nuevo;
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

    public ArrayList<Bullet> getListBullet() {
        return listBullet;
    }
    public ArrayList<Bullet> getListBulletEnemy() {
        return listBulletEnemy;
    }

    public void deadShoot(Bullet shoot, int i) {
        shoot.setY(1000);
        listBulletDead.add(shoot);
        listBullet.remove(i);
    }
    public void deadShootEnemy(Bullet shoot, int i) {
        shoot.setY(-10);
        listBulletDeadEnemy.add(shoot);
        listBulletEnemy.remove(i);
    }

    public void saveShoot(float positionX, float positionY) {
        if (listBulletDead.isEmpty()) {
            listBullet.add(new Bullet(positionX, positionY, true));
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
            listBulletEnemy.add(new Bullet(positionX, positionY, false));
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
