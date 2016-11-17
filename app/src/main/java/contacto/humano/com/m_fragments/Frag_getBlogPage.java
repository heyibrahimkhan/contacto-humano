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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import contacto.humano.com.R;
import contacto.humano.com.get_data_async.getBlogFull;
import contacto.humano.com.m_adapters.blog.adapter_blog_si;
import contacto.humano.com.m_adapters.blog.adapter_short_blog;
import contacto.humano.com.m_interfaces.i_general_array;
import contacto.humano.com.m_interfaces.i_general_string;
import contacto.humano.com.utils.BitmapWorkerTask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_getBlogPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_getBlogPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_getBlogPage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArrayList<LinearLayout> lls;
    private ArrayList mInterfaces;
    private ArrayList<View> mVs;
    private View mView;
    private String blogUrl;
    private RecyclerView si_rv;
    private ArrayList<TextView> tvs;
    private ImageView iv;
    private i_general_string igs;

    public Frag_getBlogPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_getBlogPage.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_getBlogPage newInstance(String param1, String param2) {
        Frag_getBlogPage fragment = new Frag_getBlogPage();
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
            blogUrl = mParam2;
            System.out.println("Blog Url = "+blogUrl);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.frag_getblogpage, container, false);
        initVars(inflater);
        return mView;
    }

    private void initVars(LayoutInflater inflater) {
        igs = new i_general_string() {
            @Override
            public void onStringTransfer(String string) {
                mListener.forNewFragment("BlogPage", "BlogPage", string);
            }
        };
        tvs = new ArrayList<>();
        mVs = new ArrayList<>();
        lls = new ArrayList<>();
        mInterfaces = new ArrayList();
        lls.add((LinearLayout) mView.findViewById(R.id.fbp_l));
        lls.add((LinearLayout) mView.findViewById(R.id.fbp_l2));
        mVs.add(inflater.inflate(R.layout.item_blog_side, null));
        si_rv = (RecyclerView) mVs.get(0).findViewById(R.id.isb_rv);
        si_rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        si_rv.setItemAnimator(new DefaultItemAnimator());
        mVs.add(inflater.inflate(R.layout.item_blog_short, null));
        lls.get(0).addView(mVs.get(0));
        lls.get(1).addView(mVs.get(1));
    }

    @Override
    public void onResume() {
        super.onResume();
        mInterfaces.add(new i_general_array() {
            @Override
            public void onArrayListLoaded(final ArrayList list) {
                for (int i = 0; i < list.size(); i++){
                    System.out.println("my new 1 list item = "+list.get(i).toString());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((TextView)mVs.get(0).findViewById(R.id.isb_title)).setText(list.get(0).toString());
                        }
                    });
                    si_rv.post(new Runnable() {
                        @Override
                        public void run() {
                            int len = list.size();
                            ArrayList mList = new ArrayList<adapter_blog_si.blog_side_item>();
                            for (int i = 1; i < len; i+=2) {
                                mList.add(new adapter_blog_si.blog_side_item(list.get(i).toString() ,list.get(i+1).toString()));
                            }
                            si_rv.setAdapter(new adapter_blog_si(getActivity().getApplicationContext(), mList, igs));
                        }
                    });
                }
            }
        });
        mInterfaces.add(new i_general_array() {
            @Override
            public void onArrayListLoaded(ArrayList list) {
                for (int i = 0; i < list.size(); i++){
                    adapter_short_blog.short_blog obj = (adapter_short_blog.short_blog) list.get(i);
//                    System.out.println("my new 2 list item = " + obj.getTitle());
//                    System.out.println("my new 2 list item = " + obj.getCategory());
//                    System.out.println("my new 2 list item = " + obj.getComments());
//                    System.out.println("my new 2 list item = " + obj.getDate());
//                    System.out.println("my new 2 list item = " + obj.getPostedBy());
//                    System.out.println("my new 2 list item = " + obj.getText());
//                    System.out.println("my new 2 list item = " + obj.getUrlIv());
//                    System.out.println("my new 29 list item = " + obj.getUrlReadMore());
                    tvs.add(((TextView) mVs.get(1).findViewById(R.id.ibs_title)));
                    tvs.add(((TextView) mVs.get(1).findViewById(R.id.ibs_tv_postedby)));
                    tvs.add(((TextView) mVs.get(1).findViewById(R.id.ibs_tv_postmat)));
                    tvs.add(((TextView) mVs.get(1).findViewById(R.id.ibs_tv_comments)));
                    tvs.add(((TextView) mVs.get(1).findViewById(R.id.ibs_tv_category)));
                    tvs.add(((TextView) mVs.get(1).findViewById(R.id.ibs_tv_time)));
                    iv = (ImageView) mVs.get(1).findViewById(R.id.ibs_iv_post);
                    mSetText(tvs.get(0), obj.getTitle());
                    mSetText(tvs.get(1), obj.getPostedBy());
                    mSetText(tvs.get(2), obj.getText());
                    mSetText(tvs.get(3), obj.getComments());
                    mSetText(tvs.get(4), obj.getCategory());
                    mSetText(tvs.get(5), obj.getDate());
                    new BitmapWorkerTask(iv, obj.getUrlIv(), null).execute();
                }
            }
        });
        new getBlogFull(blogUrl, mInterfaces).execute();
    }

    private void mSetText(final TextView tv, final String text){
        tv.post(new Runnable() {
            @Override
            public void run() {
                tv.setText(text);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri.toString());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
        void onFragmentInteraction(String string);
        void forNewFragment(String type_source, String type_destination, String param);
    }
}
