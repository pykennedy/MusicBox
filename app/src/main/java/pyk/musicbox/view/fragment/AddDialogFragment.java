package pyk.musicbox.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import pyk.musicbox.R;

public class AddDialogFragment extends DialogFragment {
  public interface AddDialogListener {
    void onGroupClick(DialogFragment dialog, String name);
    void onPlaylistClick(DialogFragment dialog, String name);
  }
  
  AddDialogListener listener;
  
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog dialog  = new AlertDialog.Builder(getActivity()).create();
    final LayoutInflater inflater = requireActivity().getLayoutInflater();
    final View                 view = inflater.inflate(R.layout.dialog_add, null);
    final EditText name = view.findViewById(R.id.et_name_dialogAdd);
    listener = (AddDialogListener) getTargetFragment();
    
    dialog.setView(view);
    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Create Playlist",
                     new DialogInterface.OnClickListener() {
                       @Override public void onClick(DialogInterface dialogInterface, int i) {
                         listener.onPlaylistClick(AddDialogFragment.this, name.getText().toString());
                       }
                     });
    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Create Group",
                     new DialogInterface.OnClickListener() {
                       @Override public void onClick(DialogInterface dialogInterface, int i) {
                         listener.onGroupClick(AddDialogFragment.this, name.getText().toString());
                       }
                     });
    dialog.show();
    
    Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
    Button negative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) positive.getLayoutParams();
    layoutParams.weight = 10;
    positive.setLayoutParams(layoutParams);
    negative.setLayoutParams(layoutParams);
    
    return dialog;
  }
}
