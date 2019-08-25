package com.example.appconsumos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoriasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasFragment extends Fragment
        implements CategoriasAdapter.OnCategoriasListener,
        SearchView.OnQueryTextListener
        //activar solo si se desa cambiar expansion de searchview
        //,MenuItem.OnActionExpandListener
        {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Implementando RecyclerView, es una version mejorada del ListView!
    private List<Categorias> categoriasList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CategoriasAdapter mAdapter;

    SearchView categoriasSearchView;

    public CategoriasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriasFragment newInstance(String param1, String param2) {
        CategoriasFragment fragment = new CategoriasFragment();
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

        View categoriaView = inflater.inflate(R.layout.fragment_categorias, container, false);

        //Implementando RecyclerView, es una version mejorada del ListView!
        recyclerView = (RecyclerView) categoriaView.findViewById(R.id.recycler_view_categorias);

        //El adapter debe estar creado desde antes para ser invocado
        mAdapter = new CategoriasAdapter(categoriasList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        prepareCategoriaData();

        //activar funciones de menu (paso 1)
        setHasOptionsMenu(true);

        return categoriaView;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fragmento1, container, false);
    }

    private void prepareCategoriaData() {
        Categorias categoria = new Categorias("1","Carnes");
        categoriasList.add(categoria);

        categoria = new Categorias("2","Frutas");
        categoriasList.add(categoria);

        categoria = new Categorias("3","Bebidas");
        categoriasList.add(categoria);

        categoria = new Categorias("4","Vegetales");
        categoriasList.add(categoria);

        categoria = new Categorias("5","Lacteos");
        categoriasList.add(categoria);

        categoria = new Categorias("6","Pescados y Mariscos");
        categoriasList.add(categoria);

        categoria = new Categorias("7","Otros");
        categoriasList.add(categoria);


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
    public void onCategoriasClick(int position) {
        //alimentosList.get(position);
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Log.i("====>","Click en ...CATEGORIAS FRAGMENT!!" + position);
        ft.replace(R.id.contenedor, new AlimentosFragment());
        ft.commit();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //inflater.inflate(R.menu.main, menu); validar si se vuelve activar en el fragment
        // Associate searchable configuration with the SearchView
        final MenuItem searchItem = menu.findItem(R.id.id_bar_search_alimento);
        //MenuItemCompat.setShowAsAction(searchItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS | MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        categoriasSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        categoriasSearchView.setOnQueryTextListener(this);

        int searchSrcTextId = getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = (EditText) categoriasSearchView.findViewById(searchSrcTextId);
        searchEditText.setHint("Buscar Categorías");

        super.onCreateOptionsMenu(menu, inflater);
    }
    /* validar si se vuelva a activar en el fragment
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }*/

    @Override
    public boolean onQueryTextSubmit(String newText) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (newText == null || newText.trim().isEmpty()) {
            mAdapter.setFilter(categoriasList);
            Log.i("====>","onQueryTextChange Categorias (null):!!"+newText);
            prepareCategoriaData();
            return false;
        }
        newText = newText.toLowerCase();
        final ArrayList<Categorias> filteredNewsList = new ArrayList<>();
        for (Categorias model : categoriasList) {
            final String title = model.getDesc_categoria().toLowerCase();
            /*final String author = model.getAuthor().toLowerCase();
            final String category = model.getCategory().toLowerCase();
            final String content = model.getContent().toLowerCase();*/
            if ((title.contains(newText)) /*|| (author.contains(newText)) || (category.contains(newText)) || (content.contains(newText))*/) {
                filteredNewsList.add(model);
            }
        }
        mAdapter.setFilter(filteredNewsList);
        return true;
    }
    //validar uso luego de activar implement:MenuItem.OnActionExpandListener:
    /*
    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        mAdapter.setFilter(categoriasList);
        return true;
    }*/

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
