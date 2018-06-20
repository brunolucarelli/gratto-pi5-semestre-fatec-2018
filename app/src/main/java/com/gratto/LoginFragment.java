package com.gratto;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment{

    /* Declara as variaveis de referencias dos componentes */
    private EditText txtEmail;
    private EditText txtSenha;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);



        Button btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Fragment fragment = new ProfileFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_main, fragment).addToBackStack("");
                fragmentTransaction.commit(); */

                /* Busca as referencias dos componentes que estão na view e as atribui nas variaveis declaradas */
                txtEmail = (EditText) getView().findViewById(R.id.txtEmail);
                txtSenha = (EditText) getView().findViewById(R.id.txtPassword);

                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();
                if(!email.equals("") && !senha.equals("")) {
                    loginUsuario(txtEmail.getText().toString(), txtSenha.getText().toString());
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "Não deixe nenhum campo em branco!", Toast.LENGTH_LONG).show();
                }

            }
        });

        TextView lblForgotPassword = (TextView) view.findViewById(R.id.lblForgotPassword);
        lblForgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction().replace(R.id.content_main, new ForgotPasswordFragment()).addToBackStack("").commit();
            }
        });

        TextView lblRegister = (TextView) view.findViewById(R.id.lblRegister);
        lblRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager().beginTransaction().replace(R.id.content_main, new RegisterFragment()).addToBackStack("").commit();
            }
        });
        return view;
    }

    /* Método fara a requisição ao webservice para confirmar o login  */
    public void loginUsuario(final String email, final String senha) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        RequestHelper req = new RequestHelper(this, Constantes.urlLogin, new RequestHelper.IRequests() {
            @Override
            public void Pre() {
                progressDialog.setMessage("Por favor, aguarde...");
                progressDialog.show();
            }
            @Override
            public void Pos(JSONObject jsonObject) throws JSONException {
                progressDialog.dismiss();
                if(jsonObject.getInt("status_requisicao") == 1){
                    //Toast.makeText(getActivity().getApplicationContext(), "Login realizado com sucesso!", Toast.LENGTH_LONG).show();
                    //Recupera o nome do usuario que efetuou o login (retorno do webservice)
                   // String nome     = new JSONObject(jsonObject.getString("retorno")).getString("nome");

                    Fragment fragment = new ProfileFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_main, fragment).addToBackStack("");
                    //EditText titName = (EditText)getView().findViewById(R.id.txtName);
                    //titName.setText();
                    fragmentTransaction.commit();

                }
                else if(jsonObject.getInt("status_requisicao") == 2){
                    Toast.makeText(getActivity().getApplicationContext(), "E-mail e/ou senha invalido(s)", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), jsonObject.getString("msg"), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void Error() {
                progressDialog.dismiss();
                Toast.makeText(getActivity().getApplicationContext(), "Não foi possivel realizar o procedimento, verifique sua conexão!", Toast.LENGTH_LONG).show();
            }
            @Override
            public HashMap<String, String> params() {
                HashMap hashMap = new HashMap();
                hashMap.put("email", email);
                hashMap.put("senha", senha);
                return hashMap;
            }
        });
        req.execRequest();
    }

}
