package com.nathaniel.rxharmony.reflex;

import com.nathaniel.rxharmony.entity.BaseEntity;
import com.nathaniel.rxharmony.entity.UserEntity;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

/**
 * @author nathaniel
 * @version 1.0.0
 * @contact <a href="mailto:nathanwriting@126.com">contact me</a>
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("/user/register")
    Observable<BaseEntity> register(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("/user/login")
    Observable<BaseEntity<UserEntity>> login(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("/user/updatePassword")
    Observable<BaseEntity> reset(@FieldMap Map<String, Object> parameters);

    @FormUrlEncoded
    @POST("/user/deleteUser")
    Observable<BaseEntity> logout(@FieldMap Map<String, Object> parameters);
}
