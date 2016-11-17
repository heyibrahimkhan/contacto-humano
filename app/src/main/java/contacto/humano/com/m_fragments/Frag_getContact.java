package contacto.humano.com.m_fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import at.markushi.ui.CircleButton;
import contacto.humano.com.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_getContact.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_getContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_getContact extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private Spinner spinner;
    private TextView tv_sub;
    private CircleButton in;
    private CircleButton fb;
    private CircleButton gp;

    public Frag_getContact() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_getContact.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_getContact newInstance(String param1, String param2) {
        Frag_getContact fragment = new Frag_getContact();
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
        mView =  inflater.inflate(R.layout.frag_contact, container, false);
        initVars();
        return mView;
    }

    private void initVars() {
        tv_sub = (TextView) mView.findViewById(R.id.f_con_subject);
        spinner = (Spinner) mView.findViewById(R.id.contact_sub_spin);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
            R.array.subject, android.R.layout.simple_spinner_item);
//        Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                System.out.println("Item = "+adapterView.getItemAtPosition(i));
                tv_sub.setText(adapterView.getItemAtPosition(i)+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tv_sub.setText(""+adapterView.getItemAtPosition(0));
            }
        });
        fb = (at.markushi.ui.CircleButton) mView.findViewById(R.id.b_share_fb);
        in = (at.markushi.ui.CircleButton) mView.findViewById(R.id.b_share_in);
        gp = (at.markushi.ui.CircleButton) mView.findViewById(R.id.b_share_gp);
        fb.setOnClickListener(this);
        in.setOnClickListener(this);
        gp.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if(view == fb) startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/contactohumanoconsulting/?_rdr")));
        else if (view == in) startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/con%E2%80%A2tacto-humano?trk=biz-companies-cym")));
        else if (view == gp) startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/+Contactohumanoconsulting/posts")));
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
