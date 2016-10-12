package contacto.humano.com.m_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.get_data_async.getAboutUs;
import contacto.humano.com.m_interfaces.about_us.i_about_us;
import contacto.humano.com.utils.BitmapWorkerTask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_getAbout.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_getAbout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_getAbout extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private ImageView iv;
    private ArrayList<Object> interfaces;
    private ArrayList<TextView> tvs;

    public Frag_getAbout() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_getAbout.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_getAbout newInstance(String param1, String param2) {
        Frag_getAbout fragment = new Frag_getAbout();
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
        mView =  inflater.inflate(R.layout.frag_about_us, container, false);
        initVars();
        return mView;
    }

    private void initVars() {
        interfaces = new ArrayList<>();
        tvs = new ArrayList<>();
        tvs.add((TextView) mView.findViewById(R.id.f_about_tv1));
        tvs.add((TextView) mView.findViewById(R.id.f_about_tv2));
        tvs.add((TextView) mView.findViewById(R.id.f_about_tv3));
        iv = (ImageView) mView.findViewById(R.id.f_about_iv);
    }

    @Override
    public void onResume() {
        super.onResume();
        interfaces.add(new i_about_us() {
            @Override
            public void onAboutUsLoaded(final ArrayList<String> list) {
                System.out.println("About us Loaded");
                int len = list.size();
                if(len == 4){
                    new BitmapWorkerTask(iv, list.get(0), null).execute();
                    for(int i = 1; i < len; i++){
                        final int j = i;
                        tvs.get(j).post(new Runnable() {
                            @Override
                            public void run() {
                                tvs.get(j-1).setText(list.get(j));
                            }
                        });
                    }
                }
            }
        });
        new getAboutUs(interfaces).execute();
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