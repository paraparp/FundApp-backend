package com.paraparp.gestorfondos.service.base;


import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.repository.ISymbolRepository;


@Service
public class SymbolServicePag extends BaseService<Symbol, Long, ISymbolRepository>{

}
