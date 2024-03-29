package com.example.appconsumos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlimentosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlimentosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlimentosFragment extends Fragment implements AlimentosAdapter.OnAlimentosListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AlimentosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlimentosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlimentosFragment newInstance(String param1, String param2) {
        AlimentosFragment fragment = new AlimentosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    //Implementando RecyclerView, es una version mejorada del ListView!
    private List<Alimentos> alimentosList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AlimentosAdapter mAdapter;


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

        View alimentoView = inflater.inflate(R.layout.fragment_alimentos, container, false);

        //Implementando RecyclerView, es una version mejorada del ListView!
        recyclerView = (RecyclerView) alimentoView.findViewById(R.id.recycler_view_alimentos);

        //El adapter debe estar creado desde antes para ser invocado
        mAdapter = new AlimentosAdapter(alimentosList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        prepareAlimentosData();

        //añadir acciones al boton, para salto de fragmento alimentos a fragmento registro de nuevos alimentos
        Button btnIrNuevoAlimento = (Button) alimentoView.findViewById(R.id.buttonirNuevoAlimento);
        btnIrNuevoAlimento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Log.i("====>","Click en ...boton NUEVO!!");
                ft.replace(R.id.contenedor, new NuevoAlimentoFragment());
                ft.commit();
            }
        });

        return alimentoView;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fragmento1, container, false);
    }

    private void prepareAlimentosData() {
        Alimentos alimento = new Alimentos("1","pollo entero","18/01/2019", "carnes","unidades: 2","kg","null","60 días por vencer");
        alimentosList.add(alimento);

        alimento = new Alimentos("2","medallones de lomito","18/01/2019", "carnes","unidades: 2","kg","null","14 días por vencer");
        alimentosList.add(alimento);

        alimento = new Alimentos("3","Alitas de Pollo","18/01/2019", "carnes","unidades: 2","kg","null","5 días por vencer");
        alimentosList.add(alimento);

        alimento = new Alimentos("4","Chuletas de Cerdo","18/01/2019", "carnes","unidades: 2","kg","null","3 días por vencer");
        alimentosList.add(alimento);

        alimento = new Alimentos("5","Menudencia de pollo","18/01/2019", "carnes","unidades: 2","kg","null","7 días por vencer");
        alimentosList.add(alimento);

        alimento = new Alimentos("6","churrasco","18/01/2019", "carnes","unidades: 2","kg","null","60 días por vencer");
        alimentosList.add(alimento);

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

    @Override
    public void onAlimentosClick(int position) {
        //alimentosList.get(position);
        /*FragmentTransaction ft = getFragmentManager().beginTransaction();

        Log.i("====>","Click en ...ALIMENTOS FRAGMENT!!" + position);
        ft.replace(R.id.contenedor, new RenovarAlimentoFragment());
        ft.commit();*/
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        switch (item.getItemId()){
            case 100:
                Log.i("====>","Click en ...RENOVAR ALIMENTO!!");
                ft.replace(R.id.contenedor, new RenovarAlimentoFragment());
                ft.commit();
                return true;
            case 101:
                Log.i("====>","Click en ...ELIMINAR ALIMENTO!!");
                ft.replace(R.id.contenedor, new EliminarAlimentoFragment());
                ft.commit();
                return true;
        }

        return super.onContextItemSelected(item);

    }
}
