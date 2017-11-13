package dao;

import entity.Clazz;

import java.util.List;

public interface IClazz {

    int insertClazzBatch(List<Clazz> list);
}
