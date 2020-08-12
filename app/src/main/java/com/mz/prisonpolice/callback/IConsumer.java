package com.mz.prisonpolice.callback;

/**
 * @author Muzzz
 * @CreateTime 2020/8/12
 * @Desc
 */
public interface IConsumer<T> {
    void accept(T t);

    void accept(Throwable throwable);
}
