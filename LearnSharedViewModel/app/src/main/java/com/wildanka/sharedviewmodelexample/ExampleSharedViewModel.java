package com.wildanka.sharedviewmodelexample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ExampleSharedViewModel extends ViewModel {
    public final MutableLiveData<String> name = new MutableLiveData<String>();
    private MutableLiveData<String> address = new MutableLiveData<String>();
    private MutableLiveData<String> age = new MutableLiveData<String>();
    private MutableLiveData<String> phoneNumber = new MutableLiveData<String>();

    public LiveData<String> getName() {
        return name;
    }

    public LiveData<String> getAddress() {
        return address;
    }

    public LiveData<String> getAge() {
        return age;
    }

    public LiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name){
        this.phoneNumber.setValue(name);
    }

    public void setAddress(String address) {
        this.address.setValue(address);
    }

    public void setAge(String age) {
        this.age.setValue(age);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setValue(phoneNumber);
    }
}
