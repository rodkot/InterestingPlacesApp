package com.rodix.lab3.mapper;

public interface DtoModelMapper<DtoModel,Model> {
    DtoModel mapFromModel(Model model);
    Model mapToModel(DtoModel model);
}
