package com.example.appconsumos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SupermercadosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SupermercadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SupermercadosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SupermercadosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SupermercadosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SupermercadosFragment newInstance(String param1, String param2) {
        SupermercadosFragment fragment = new SupermercadosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //Implementando RecyclerView, es una version mejorada del ListView!
    private List<Supermercados> supermercadosList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SupermercadosAdapter mAdapter;


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

        View alimentoView = inflater.inflate(R.layout.fragment_contactar_supermercado, container, false);

        //Implementando RecyclerView, es una version mejorada del ListView!
        recyclerView = (RecyclerView) alimentoView.findViewById(R.id.recycler_view_contactsupmercados);

        //El adapter debe estar creado desde antes para ser invocado
        mAdapter = new SupermercadosAdapter(supermercadosList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        prepareSMercadosData();

        return alimentoView;
    }


    private void prepareSMercadosData() {
        Supermercados smercado = new Supermercados("1","Tottus - San Miguel");
        supermercadosList.add(smercado);

        smercado = new Supermercados("2","Tottus - San Isidro");
        supermercadosList.add(smercado);

        smercado = new Supermercados("3","Plaza Vea - Barranco");
        supermercadosList.add(smercado);

        smercado = new Supermercados("4","Plaza Vea - San Isidro");
        supermercadosList.add(smercado);


        mAdapter.notifyDataSetChanged();
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
        void onFragmentInteraction(Uri uri);
    }
}
