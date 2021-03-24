package com.erp.distribution.desgreenrestkt.presentation.rest_controller

import org.slf4j.LoggerFactory

object FParamDiskonNotaRestController {
    private val logger = LoggerFactory.getLogger(FParamDiskonNotaRestController::class.java) //    @Autowired
    //    FParamDiskonNotaJPARepository fParamDiskonNotaJPARepository;
    //
    //
    //    @RequestMapping(value = "/rest/getFParamDiskonNotaById/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    //    public FParamDiskonNota getFParamDiskonNotaById(@PathVariable("id") int id){
    //        return fParamDiskonNotaJPARepository.findById(id);
    //    }
    //
    //
    //    @RequestMapping(value = "/rest/getAllFParamDiskonNota", produces = {MediaType.APPLICATION_JSON_VALUE} )
    //    public List<FParamDiskonNota> getAllParamDiskonNota(){
    //        return fParamDiskonNotaJPARepository.findAll();
    //    }
    //    @RequestMapping(value = "/rest/getAllFParamDiskonNotaByDivision/{fdivisionBean}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    //    public List<FParamDiskonNota> getAllFParamDiskonNotaByDivision(@PathVariable("fdivisionBean") int fdivisionBean){
    //        return fParamDiskonNotaJPARepository.findAllByDivision(fdivisionBean);
    //    }
    //
    //    @RequestMapping(value = "/rest/createFParamDiskonNota", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    //    public FParamDiskonNota createFParamDiskonNota(@RequestBody FParamDiskonNota fParamDiskonNotaNew) {
    //        return fParamDiskonNotaJPARepository.save(fParamDiskonNotaNew);
    //    }
    //
    //    @RequestMapping(value = "/rest/updateFParamDiskonNota/{id}", method = RequestMethod.PUT )
    //    public FParamDiskonNota updateFParamDiskonNotaInfo(@PathVariable("id") Integer id, @RequestBody FParamDiskonNota fParamDiskonNotaUpdated){
    //        FParamDiskonNota fParamDiskonNota = fParamDiskonNotaJPARepository.findById(id).orElse(new FParamDiskonNota());
    //        //Tidak Meng Update Parent: Hanya Info Saja
    //        if (fParamDiskonNotaUpdated !=null) {
    //            fParamDiskonNotaUpdated.setId(fParamDiskonNota.getId());
    //            if (fParamDiskonNota.getFdivisionBean()==null) fParamDiskonNotaUpdated.setFdivisionBean(fParamDiskonNota.getFdivisionBean());
    //
    //            fParamDiskonNotaJPARepository.save(fParamDiskonNotaUpdated);
    //
    //            return fParamDiskonNotaUpdated;
    //        }
    //
    //        return fParamDiskonNota;
    //    }
    //
    //    @RequestMapping(value = "/rest/deleteFParamDiskonNota/{id}", method = RequestMethod.DELETE )
    //    public FParamDiskonNota deleteFParamDiskonNota(@PathVariable("id") Integer id){
    //        FParamDiskonNota fParamDiskonNota = fParamDiskonNotaJPARepository.findById(id).orElse(new FParamDiskonNota());
    //        if (fParamDiskonNota !=null) {
    //            fParamDiskonNotaJPARepository.delete(fParamDiskonNota);
    //        }
    //        return fParamDiskonNota;
    //    }
}