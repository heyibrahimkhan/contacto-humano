package contacto.humano.com.m_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.get_data_async.getServices;
import contacto.humano.com.m_adapters.services.adapter_services;
import contacto.humano.com.m_interfaces.i_general_array;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_getServices.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_getServices#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_getServices extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private RecyclerView rv;
    private ArrayList mInterfaces;

    public Frag_getServices() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_getServices.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_getServices newInstance(String param1, String param2) {
        Frag_getServices fragment = new Frag_getServices();
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
        mView = inflater.inflate(R.layout.frag_services, container, false);
        initVars();
        return mView;
    }

    private void initVars() {
        mInterfaces = new ArrayList<>();
        rv = (RecyclerView) mView.findViewById(R.id.f_sevices_rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onResume() {
        super.onResume();
        mInterfaces.add(new i_general_array() {
            @Override
            public void onArrayListLoaded(final ArrayList list) {
                rv.post(new Runnable() {
                    @Override
                    public void run() {
                        if(mParam2.equalsIgnoreCase("academics")){
                            rv.setAdapter(new adapter_services(list));
                        }
                        else if(mParam2.equalsIgnoreCase("professionals")){

                        }
                    }
                });
            }
        });
        new getServices(mParam1, mParam2, mInterfaces).execute();
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
