package com.hilonggroupmes.service.process.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hilonggroupmes.dao.basedata.ProductDao;
import com.hilonggroupmes.dao.process.ProcedureDao;
import com.hilonggroupmes.dao.process.ProcedureItemDao;
import com.hilonggroupmes.dao.process.ProductProcessDao;
import com.hilonggroupmes.domain.basedata.ProductInfo;
import com.hilonggroupmes.domain.process.ProcedureInfo;
import com.hilonggroupmes.domain.process.ProcedureItemInfo;
import com.hilonggroupmes.domain.process.ProductProcessInfo;
import com.hilonggroupmes.service.process.ProcedureService;

@Service("procedureService")
public class ProcedureServiceImpl implements ProcedureService {
	
	@Resource
	private ProcedureDao procedureDao;
	
	@Resource
	private ProductDao productDao;
	
	@Resource
	private ProcedureItemDao procedureItemDao;
	
	@Resource
	private ProductProcessDao productProcessDao;

	@Override
	public Long getProcedureNum(Map<String, Object> queryPare) {
		return procedureDao.getProcedureNum(queryPare);
	}

	@Override
	public List<ProcedureInfo> findProcedureByPage(Integer page, Integer rows,
			Map<String, Object> queryPare) {
		
		return procedureDao.getProcedureByPage(page, rows, queryPare);
	}

	@Override
	public Boolean deleteProcedureByIds(String procedureIds) {
		try{
		List<ProcedureInfo> pi = procedureDao.getProcedureListByIds(procedureIds);
		for(int i=0;i<pi.size();i++)
			procedureDao.delete(pi.get(i));
		}catch(Exception e){
			System.out.print(e.toString());
			return false;
		}
		return true;
	}

	@Override
	public ProcedureInfo findProcedureById(Long procedure_id) {
		return procedureDao.get(ProcedureInfo.class, procedure_id);
	}

	@Override
	public Long saveProcedure(ProcedureInfo procedure) {		
		return (Long)procedureDao.save(procedure);
	}

	@Override
	public void updateProcedure(ProcedureInfo procedure) {
		 procedureDao.update(procedure);

	}

	@Override
	public List<ProcedureItemInfo> getProcedureItemByProcedure(Long procedure_id) {
		ProcedureInfo p = procedureDao.get(ProcedureInfo.class, procedure_id);
		return p.getProcedure_items();
	}

	@Override
	public void saveProcedureItem(ProcedureItemInfo procedure_item) {
		procedureItemDao.saveOrUpdate(procedure_item);
		
	}

	@Override
	public List<ProcedureInfo> getProcedureList() {
		return procedureDao.getProcedureList();
	}

	@Override
	public List<ProductProcessInfo> getProductProcessByProduct(
			Long productprocess_id) {
		List<ProductProcessInfo> ppilist = null;
		ProductInfo pi = productDao.getProductById(productprocess_id);
		if(pi!=null)
		{
			ppilist = productProcessDao.getProductProcessByProduct(pi);
		}
		return ppilist;
	}
}
