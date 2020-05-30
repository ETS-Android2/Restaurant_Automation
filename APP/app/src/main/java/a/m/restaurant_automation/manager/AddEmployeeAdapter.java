package a.m.restaurant_automation.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.responseModel.RegisterResponseModel;
import a.m.restaurant_automation.responseModel.UsersResponseModel;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddEmployeeAdapter extends RecyclerView.Adapter<AddEmployeeAdapter.ViewHolder> {
        private ArrayList<UsersResponseModel> registerResponse;
        int size = 0;
        private Context context;
        public View.OnClickListener onItemListener;



        public AddEmployeeAdapter(ArrayList<UsersResponseModel> registerResponse, Context context) {
            this.registerResponse = registerResponse;
            size = this.registerResponse.size();
            this.context = context;
        }

        @NonNull
        @Override
        public AddEmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_employee_list,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AddEmployeeAdapter.ViewHolder holder, int position) {
            holder.employeeFirstName.setText(registerResponse.get(position).getFirstName());
            holder.employeeLastName.setText(registerResponse.get(position).getLastName());
            holder.employeePhone.setText(registerResponse.get(position).getPhone());
            holder.employeeUserType.setText(registerResponse.get(position).getUserType().toString());
            holder.employeeEmail.setText(registerResponse.get(position).getEmail());
            //Picasso.get(position).load().into(holder.employeeImage);

        }

        @Override
        public int getItemCount() {
            return size;
        }

        public void setOnItemClickListener(View.OnClickListener onClickListener) {
            onItemListener = onClickListener;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView employeeFirstName, employeeLastName,employeeEmail,employeePhone,employeeUserType;
            //Button removeItemButton;
            ImageView employeeImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                employeeFirstName= itemView.findViewById(R.id.employeeFirstName);
                employeeLastName = itemView.findViewById(R.id.employeeLastName);
                employeeEmail = itemView.findViewById(R.id.employeeEmail);
                employeePhone = itemView.findViewById(R.id.employeePhone);
                employeeUserType = itemView.findViewById(R.id.employeeUserType);
                employeeImage=itemView.findViewById(R.id.imageEmployee);

                //removeItemButton.setOnClickListener(onItemListener);
                itemView.setTag(this);
            }
        }
    }


