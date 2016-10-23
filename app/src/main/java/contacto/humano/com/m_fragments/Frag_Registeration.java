package contacto.humano.com.m_fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.get_data_async.register.mGetJSONData;
import contacto.humano.com.m_interfaces.i_general_string;
import contacto.humano.com.m_interfaces.i_process_dialog;
import contacto.humano.com.utils.mConstants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_Registeration.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_Registeration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_Registeration extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private ArrayList<View> mVs;
    private LinearLayout mRoot;
    private Button mRegister;
    private ArrayList i_nonce, i_register;
    private ProgressDialog progressDialog;
    private ArrayList<EditText> ets;

    public Frag_Registeration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_Registeration.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_Registeration newInstance(String param1, String param2) {
        Frag_Registeration fragment = new Frag_Registeration();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.frag_registeration, container, false);
        initVars(inflater);
        return mView;
    }

    private void initVars(LayoutInflater inflater) {
        ets = new ArrayList<>();
        i_register = new ArrayList();
        i_nonce = new ArrayList();
        mRoot = (LinearLayout) mView.findViewById(R.id.fr_mroot);
        mRegister = (Button) mView.findViewById(R.id.fr_b_register);

        mVs = new ArrayList<>();
        mVs.add(inflater.inflate(R.layout.i_detail_acc, null)); // account details layout added
        ets.add((EditText) mVs.get(0).findViewById(R.id.fr_et_username));
        ets.add((EditText) mVs.get(0).findViewById(R.id.fr_et_email));
        ets.add((EditText) mVs.get(0).findViewById(R.id.fr_et_pass));
        ets.add((EditText) mVs.get(0).findViewById(R.id.fr_et_passconf));

        int len_mvs = mVs.size();
        for (int i = 0; i < len_mvs; i++){
            mRoot.addView(mVs.get(i));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Logic for cleaning parameters like checking password match, correct inputs and whatnot
                 */
                new mGetJSONData(mConstants.url_nonce_register, i_nonce).execute(); //Nonce Will be genertaed by this
            }
        });
        i_register.add(new i_process_dialog() {
            @Override
            public void onDataBeingLoaded() {
                showProcessDialog("Registering");
            }

            @Override
            public void onDataLoadingComplete() {
                dismissProgressDialog();
            }
        });
        i_register.add(new i_general_string() {
            @Override
            public void onStringTransfer(String string) {
                String status = "", cookie = "", user_id = "", error_message = "";
                try {
                    JSONObject obj = new JSONObject(string);
                    if(obj.has("status")) {
                        status = obj.getString("status");
                        if(status.equals("ok")){
                            if(obj.has("cookie")) cookie = obj.getString("cookie");
                            if(obj.has("user_id")) user_id = obj.getString("user_id");
                            if(!cookie.equals("") && !user_id.equals("")){
                                TastyToast.makeText(getContext(), obj.toString(), TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                            }
                            /*
                            Save cookie and user_id and do whatever
                             */
                        }
                        else{
                            if(obj.has("error")) {
                                error_message = obj.getString("error");
                                TastyToast.makeText(getContext(), error_message, TastyToast.LENGTH_LONG, TastyToast.ERROR);
                            }
                        }
                    }
                    System.out.println("obj = "+obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        i_nonce.add(new i_process_dialog() {
            @Override
            public void onDataBeingLoaded() {
                showProcessDialog("Getting Nonce");
            }

            @Override
            public void onDataLoadingComplete() {
                dismissProgressDialog();
            }
        });
        i_nonce.add(new i_general_string() {
            @Override
            public void onStringTransfer(String string) {
                String status = "", method = "", nonce = "", error_message = "";
                try {
                    JSONObject obj = new JSONObject(string);
                    if(obj.has("status")) {
                        status = obj.getString("status");
                        if(status.equals("ok")){
                            if(obj.has("method")) method = obj.getString("method");
                            if(obj.has("nonce")) nonce = obj.getString("nonce");
                            if(!nonce.equals("")){
                                new mGetJSONData(getRegisterUrl(nonce), "POST", i_register).execute();
                            }
                        }
                        else{
                            if(obj.has("error")) {
                                error_message = obj.getString("error");
                                TastyToast.makeText(getContext(), error_message, TastyToast.LENGTH_LONG, TastyToast.ERROR);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getRegisterUrl(String nonce) {
        String url = "";
        url = "http://con-tactohumano.com/api/user/register/?username=" + ets.get(0).getText().toString() +
                "&email=" + ets.get(1).getText().toString() +
                "&nonce=" + nonce +
                "&display_name=" + ets.get(0).getText().toString() +
                "&notify=both" +
                "&user_pass=" + ets.get(2).getText().toString() +
                "&insecure=cool";
        //http://con-tactohumano.com/api/user/register/?username=johnn&email=johnn@domain.com&nonce=5bac73cad6
        // &display_name=John&notify=both&user_pass=1234456&insecure=cool
        return url;
    }

    private void showProcessDialog(String msg) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Getting nonce");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
