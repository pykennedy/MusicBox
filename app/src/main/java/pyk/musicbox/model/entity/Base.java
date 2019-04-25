package pyk.musicbox.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static pyk.musicbox.model.DBConstants.BaseConstants.ID;


@Entity(indices = {@Index(value = {ID}, unique = true)})
public class Base {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = ID)
  private int id;
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getId() {
    return id;
  }
}
