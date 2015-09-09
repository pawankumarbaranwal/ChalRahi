package example.android.com.chalrahi;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowError {
   public static void displayError(Context context,String message){
       final Dialog dialog = new Dialog(context,R.style.PauseDialog);
       dialog.setContentView(R.layout.error_popup);
       final TextView error = (TextView) dialog.findViewById(R.id.tvErrorMessage);
       final Button ok= (Button) dialog.findViewById(R.id.btnOK);
       error.setText(message);
       dialog.setTitle("Error");

       dialog.show();
       ok.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialog.dismiss();
           }
       });
   }

}