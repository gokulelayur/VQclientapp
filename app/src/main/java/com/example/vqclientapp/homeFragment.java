package com.example.vqclientapp;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.example.vqclientapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class homeFragment extends Fragment {

    RecyclerView recyclerView;
    adapterQueueRecycler adapterQueueRecycler;
    TextView tokName,tokPlace,tokAge,emptyRecycler;

    token currentToken = new token();
    ArrayList<Integer> queue = new ArrayList<>();
    ArrayList<token> tokenArrayList = new ArrayList<>();

    FirebaseDatabase rootNode;
    DatabaseReference reference, thisDeptRef, ref;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);



                loadingScreen.startloading(getActivity(),"Loading");        //LOADING SCREEN STARTED


        TextView currentTokenDisp = view.findViewById(R.id.display_current_token);
        recyclerView = view.findViewById(R.id.queueRecycler);
        adapterQueueRecycler = new adapterQueueRecycler(tokenArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterQueueRecycler);

        rootNode = FirebaseDatabase.getInstance();
        thisDeptRef = rootNode.getReference("main").child("company").child(SaveId.getId(getContext()))
                .child("department").child(SaveId.getDepID(getContext()));
        reference = thisDeptRef.child("BUFFER");
        ref = thisDeptRef.child("TOKEN");


        tokName=view.findViewById(R.id.currentTokenName);
        tokPlace=view.findViewById(R.id.currentTokenPlace);
        tokAge=view.findViewById(R.id.currentTokenAge);

        emptyRecycler=view.findViewById(R.id.recyclerIsEmptyText);
        //----------QUEUE RECYCLER UPDATER------------------------------------------------------------------------


        reference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                queue.clear();
                tokenArrayList.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        int Value = Integer.valueOf(dataSnapshot.getValue().toString());
                        queue.add(Value);

                    }

                    for (int i = 0; i < queue.size(); i++) {
                        Query query = ref.orderByChild("tokenNo").equalTo(queue.get(i));
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snap) {
                                for (DataSnapshot shot : snap.getChildren()) {
                                    token newtoken = shot.getValue(token.class);
                                    tokenArrayList.add(newtoken);

                                }

                                //Current Token
                                currentToken = tokenArrayList.get(0);
                                currentTokenDisp.setText(String.valueOf(currentToken.getTokenNo()));
                                tokName.setText(currentToken.getTokenName());
                                tokPlace.setText(currentToken.getTokenPlace());
                                tokAge.setText(String.valueOf(currentToken.getTokenAge()));

                                adapterQueueRecycler.notifyDataSetChanged();



                                //


                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                                Toast.makeText(getContext(), "Error Occured", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }

                if(!tokenArrayList.isEmpty()){
                    emptyRecycler.setVisibility(View.GONE);
                }
                else {
                    emptyRecycler.setVisibility(View.VISIBLE);
                }
                loadingScreen.stoploading();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getContext(), "Data Fetch Error from Main Listner", Toast.LENGTH_SHORT).show();
            }
        });

        //----------NEXT TOKEN BUTTON CLICK------------------------------------------------------------------------


        Button nextTokenButt = view.findViewById(R.id.nextTokenButt);

        nextTokenButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (queue.size() >= 1) {

                    Query query = ref.orderByChild("tokenNo").equalTo(queue.get(0));
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            String key = snapshot.getKey();
                            FirebaseDatabase.getInstance().getReference("main/pastTokens").setValue(snapshot.getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    Toast.makeText(getContext(), "Done Copiying", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                    if (queue.size() == 1) {
                        reference.removeValue();
                        queue.clear();
                        tokenArrayList.clear();
                        adapterQueueRecycler.notifyDataSetChanged();
                        currentTokenDisp.setText("NA");

                    } else {
                        queue.remove(0);
                        int i = 0;
                        Map<String, Object> map = new HashMap<>();
                        for (int k : queue) {
                            i += 1;
                            map.put(String.valueOf(i), k);

                        }
                        reference.removeValue();
                        reference.updateChildren(map);
                    }

                }
            }
        });

        //----------PASS TOKEN BUTTON CLICK------------------------------------------------------------------------


        Button passTokenButt = view.findViewById(R.id.passTokenButt);
        passTokenButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = queue.get(0);
                queue.remove(0);


                //----------BUFFER ALGORITHM------------------------------------------------------------------------

                Python py = Python.getInstance();
                PyObject pyObject = py.getModule("buggerAlgorithm");
                String buf = pyObject.callAttr("bufferAlgo", val, 3, queue.toString()).toString();
                // the argument=> 3 here is the no. of person after which late comer enters

                String[] strBuffer = buf.substring(1, buf.length() - 1).split(",");
                ArrayList<Integer> newBuffer = new ArrayList<>();
                for (String i : strBuffer) {

                    newBuffer.add(Integer.parseInt(i.replaceAll(" ", "")));
                }

                //-----------END OF BUFFER ALGORITHM----------------------------------------------------------------
                int i = 0;
                Map<String, Object> buffMap = new HashMap<>();
                for (int k : newBuffer) {
                    i += 1;
                    buffMap.put(String.valueOf(i), k);
                }
                reference.removeValue();
                reference.updateChildren(buffMap);
                Toast.makeText(getContext(), "Customer Rearranged Successfully", Toast.LENGTH_SHORT).show();

            }
        });


        //----------ADD OFFLINE CUSTOMER------------------------------------------------------------------------


        ImageButton addCust = view.findViewById(R.id.addCustomer);
        addCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog addCustDialog;
                addCustDialog = new Dialog(view.getContext());

                ImageView closeimg;
                EditText newCustName, newCustPlace, newCustAge;
                Button addNewCustomerButt;

                addCustDialog.setContentView(R.layout.addcustomerpopup);
                closeimg = addCustDialog.findViewById(R.id.popupClosebuttImg);
                newCustName = addCustDialog.findViewById(R.id.newCustName);
                newCustPlace = addCustDialog.findViewById(R.id.newCustPlace);
                newCustAge = addCustDialog.findViewById(R.id.newCustAge);
                addNewCustomerButt = addCustDialog.findViewById(R.id.popUpAddNewCust);

                addNewCustomerButt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = newCustName.getText().toString();
                        String place = newCustPlace.getText().toString();
                        int age = Integer.parseInt(newCustAge.getText().toString());

                        token newCustToken = new token();
                        newCustToken.setTokenName(name);
                        newCustToken.setTokenAge(age);
                        newCustToken.setTokenPlace(place);
                        boolean flag = false;
                        thisDeptRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                int maxT = Integer.parseInt(snapshot.child("maxtokens").getValue().toString());
                                int nextAvail = Integer.parseInt(snapshot.child("nextavailabletoken").getValue().toString());

                                if (nextAvail <= maxT) {
                                    if (nextAvail == maxT) {
                                        thisDeptRef.child("activenow").setValue(false);
                                    }

                                    thisDeptRef.child("nextavailabletoken").setValue(nextAvail + 1);
                                    newCustToken.setTokenNo(nextAvail);
                                    ref.child(newCustToken.tokenNo + "++" + SaveId.getDepID(getContext()) + "+DATE").setValue(newCustToken);


                                    //----------BUFFER ALGORITHM------------------------------------------------------------------------

                                    Python py = Python.getInstance();
                                    PyObject pyObject = py.getModule("buggerAlgorithm");
                                    String buf = pyObject.callAttr("bufferAlgo", newCustToken.tokenNo, 3, queue.toString()).toString();
                                    // the argument=> 3 here is the no. of person after which late comer enters

                                    String[] strBuffer = buf.substring(1, buf.length() - 1).split(",");
                                    ArrayList<Integer> newBuffer = new ArrayList<>();
                                    for (String i : strBuffer) {

                                        newBuffer.add(Integer.parseInt(i.replaceAll(" ", "")));
                                    }

                                    //-----------END OF BUFFER ALGORITHM----------------------------------------------------------------


                                    int i = 0;
                                    Map<String, Object> buffMap = new HashMap<>();
                                    for (int k : newBuffer) {
                                        i += 1;
                                        buffMap.put(String.valueOf(i), k);
                                    }
                                    reference.removeValue();
                                    reference.updateChildren(buffMap);


                                    Toast.makeText(getContext(), "Customer added Successfully", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getContext(), "Max Token reached", Toast.LENGTH_SHORT).show();
                                    thisDeptRef.child("activenow").setValue(false);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                                Toast.makeText(getContext(), "Unexpected Error Occured", Toast.LENGTH_SHORT).show();

                            }
                        });
                        addCustDialog.dismiss();

                    }
                });

                closeimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addCustDialog.dismiss();
                    }
                });

                addCustDialog.show();

            }
        });

        return view;
    }
}
