package com.example.ricardosernam.puntodeventa.Ventas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ricardosernam.puntodeventa.R;
import com.example.ricardosernam.puntodeventa._____interfazes.interfaz_descuento;

@SuppressLint("ValidFragment")
public class pagar_DialogFragment extends android.support.v4.app.DialogFragment {
    private Button aceptar,cancelar;
    private String pagar;
    private TextView total,cambio;
    private EditText cantidad;
    private int totalPagar;
    interfaz_descuento Interface_historial;

     @SuppressLint("ValidFragment")
     public pagar_DialogFragment(int totalPagar){
         this.totalPagar=totalPagar;
     }

    @Override
    public View onCreateView (final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView=inflater.inflate(R.layout.pagar_dialog_fragment,container);
        Interface_historial =(interfaz_descuento) getActivity();//ESTO SOLO ES POSIBLE SI MainActivity es una subclase de Comunicador por lo tanto implementa Comunicator: Polimorfismo
        this.getDialog().setTitle("Cobrar");///cambiamos titulo del DialogFragment
        total=rootView.findViewById(R.id.TVtotalCompra);
        cambio=rootView.findViewById(R.id.TVcambio);
        cantidad=rootView.findViewById(R.id.ETcantidadPago);
        aceptar=rootView.findViewById(R.id.BtnAceptarPago);
        cancelar=rootView.findViewById(R.id.BtnCancelarPago);

        total.setText(String.valueOf(totalPagar));
        cantidad.setText(String.valueOf(totalPagar));
        cantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!(TextUtils.isEmpty(cantidad.getText()))) {
                    cambio.setText(String.valueOf((Integer.parseInt(String.valueOf(cantidad.getText())))-totalPagar));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar(Integer.parseInt(String.valueOf(cantidad.getText())),totalPagar)) {
                    dismiss();
                }
                else{

                }
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return rootView;
    }
    public Boolean validar(int pago, int total){
         Boolean validado=true;
        if(pago<total || String.valueOf(pago).isEmpty()){
          validado=false;
          cantidad.setError("Ingresa una cantidad válida");
         }
        return validado;
    }

}
