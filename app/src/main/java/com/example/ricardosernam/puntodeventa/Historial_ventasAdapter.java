package com.example.ricardosernam.puntodeventa;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Historial_ventasAdapter extends RecyclerView.Adapter<Historial_ventasAdapter.HistorialVentasViewHolder> {
    private ArrayList<Historial_ventas_class> itemsHistorial;
    private Context context;

    public Historial_ventasAdapter(Context context, ArrayList<Historial_ventas_class> itemsCobrar) {  ///recibe el arrayCobrar como parametro
        this.itemsHistorial=itemsCobrar;
        this.context=context;

    }

    public class HistorialVentasViewHolder extends RecyclerView.ViewHolder {
        public TextView tipo,pagar;
        public LinearLayout estatus, fechaEntrega, deuda;
        public Button pagarDeuda;

        public HistorialVentasViewHolder(View itemView) {
            super(itemView);
            tipo=itemView.findViewById(R.id.TVtipoHistorial);
            pagar=itemView.findViewById(R.id.TVestatusHistorial);
            estatus=itemView.findViewById(R.id.LOestatus);
            pagarDeuda=itemView.findViewById(R.id.BtnPagar);
            fechaEntrega=itemView.findViewById(R.id.LOfechaEntrega);
            deuda=itemView.findViewById(R.id.LOdeuda);
        }

    }

    @Override
    public HistorialVentasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjetas_historial_ventas, parent, false);////mencionamos el archivo del layout
        return new HistorialVentasViewHolder(v);
    }
    @Override
    public int getItemCount() {
        return itemsHistorial.size();
    }
    @Override
    public void onBindViewHolder(HistorialVentasViewHolder holder, final int position) {
        final FragmentManager manager = ((Activity) context).getFragmentManager();
        holder.tipo.setText(itemsHistorial.get(position).getTipo());
        holder.pagar.setText(itemsHistorial.get(position).getPagar());
        if(itemsHistorial.get(position).getPagar().equals("Pagar ahora")){
            holder.pagar.setText("Pagado");
            holder.estatus.setBackgroundColor(Color.GREEN);
            holder.itemView.setFocusable(false);
            holder.pagarDeuda.setVisibility(View.INVISIBLE);
            holder.deuda.setVisibility(View.INVISIBLE);
        }
        else{
            holder.estatus.setBackgroundColor(Color.RED);
        }

        if(itemsHistorial.get(position).getTipo().equals("Venta")){
            holder.itemView.setBackgroundColor(Color.GRAY);
            holder.fechaEntrega.setVisibility(View.INVISIBLE);

        }
        else if(itemsHistorial.get(position).getTipo().equals("Apartado")){
            holder.itemView.setBackgroundColor(Color.CYAN);
        }
        holder.pagarDeuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Simon", Toast.LENGTH_LONG).show();
                //new pagar_DialogFragment(itemsHistorial.get(position).getTipo(),(itemsHistorial.get(position).getPagar())).show(manager,"pagarDiaogFragment");
            }
        });
    }




}
