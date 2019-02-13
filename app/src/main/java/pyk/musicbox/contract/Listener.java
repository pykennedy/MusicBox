package pyk.musicbox.contract;

import pyk.musicbox.model.dbobjects.Group;

public interface Listener {
  // TODO: need a listener for ArrayList changes, DB changes, and disk reading
  // TODO: DB listener needs to have insert/delete/update methods
  public interface GroupListTableListener {
    void onInsert(Group group);
    void onUpdate();
    void onDelete();
  }
  
  public interface GroupListListener {
    void listUpdated();
  }
}
