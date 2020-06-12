package a.m.restaurant_automation.cashier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.manager.MenuItemAdapter;
import a.m.restaurant_automation.responseModel.GetReadyForPaymentResponseModel;
import a.m.restaurant_automation.responseModel.UsersResponseModel;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CashierGetReadyPaymentAdapter extends RecyclerView.Adapter<CashierGetReadyPaymentAdapter.ViewHolder> {
    private ArrayList<GetReadyForPaymentResponseModel> getReadyForPaymentResponseModels;
    int size = 0;
    private Context context;
    public View.OnClickListener onItemListener;


    public CashierGetReadyPaymentAdapter(ArrayList<GetReadyForPaymentResponseModel> getReadyPayment,Context context) {
        this.getReadyForPaymentResponseModels=getReadyPayment;
        this.context=context;
        this.size=getReadyPayment.size();

    }

    @NonNull
    @Override
    public CashierGetReadyPaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cashier_getreadypaymentlist, parent, false);
        return new CashierGetReadyPaymentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CashierGetReadyPaymentAdapter.ViewHolder holder, int position) {

        holder.billId.setText("BillId : "+getReadyForPaymentResponseModels.get(position).getBillId());
        holder.billAmt.setText("Amount : "+getReadyForPaymentResponseModels.get(position).getBillingAmount());
        holder.tableId.setText("Table : "+getReadyForPaymentResponseModels.get(position).getTableID());
        holder.fName.setText("Name : "+getReadyForPaymentResponseModels.get(position).getFirstName());
        holder.lName.setText(" "+getReadyForPaymentResponseModels.get(position).getLastName());
        if(getReadyForPaymentResponseModels.get(position).getIcCardPayment()) {
            holder.paymentType.setText("Payment Type : "+"Card");
        }
        else
        {
            holder.paymentType.setText("Payment Type : "+"Cash");
        }


    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView billId,billAmt,tableId,fName,lName,paymentType;
        Button btnPay;
        public ViewHolder(View itemView) {
            super(itemView);

            billId = itemView.findViewById(R.id.textView_billIdGRP);
            billAmt=itemView.findViewById(R.id.textView_billingAmtGRP);
            tableId=itemView.findViewById(R.id.textView_tableIDGRP);
            fName=itemView.findViewById(R.id.textView_FirstNameGRP);
            lName=itemView.findViewById(R.id.textView_LastNameGRP);
            paymentType=itemView.findViewById(R.id.textView_paymentTypeGRP);
            btnPay=itemView.findViewById(R.id.button_paymentGRP);
            btnPay.setOnClickListener(onItemListener);
        }
    }
}
