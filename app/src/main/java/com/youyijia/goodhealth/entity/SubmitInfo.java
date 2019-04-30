package com.youyijia.goodhealth.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangqiang on 2019/1/22.
 */
public class SubmitInfo implements Serializable{

    /**
     * attachService : [{"content":"100","id":"<p>报关：100<\/p>","name":"报关","price":"100","selected":"1","valid":"1"}]
     * goodsExtensionInsurance : {"planList":{"plan1":{"costRatio":"2","insuranceFee":"66.36","payoutRatio":"20"},"plan2":{"costRatio":"4","insuranceFee":"132.72","payoutRatio":"50"},"plan3":{"costRatio":"6","insuranceFee":"199.08","payoutRatio":"100"}},"userInfo":{"companyName":"20131205测试客户","idCard":"","realName":"","taxId":"91310105776689124Y","userType":"0"},"valid":"0"}
     * orderBill : {"activityOff":"","contactInfo":[{"email":"batwuq@job-mate.com","id":"qazwsx","name":"qazwsx","qq":"28031977328","remark":"","tel":"61156552"},{"email":"123456@qq.com","id":"asdfg","name":"asdfg","qq":"123456","remark":"","tel":"123456789"},{"email":"12424435@11.cn","id":"1324","name":"1324","qq":"2242424","remark":"","tel":"244424241"},{"email":"123456@124.com","id":"qqq","name":"qqq","qq":"123456789","remark":"","tel":"12345789"},{"email":"12345678@qq.cc","id":"yanjinhang","name":"yanjinhang","qq":"12345678","remark":"","tel":"123456778"},{"email":"123454657@ee.cv","id":"123123","name":"123123","qq":"1234567","remark":"","tel":"12345678"},{"email":" ","id":" 联系方式1","name":" ","qq":" ","remark":"","tel":" "},{"email":" ","id":" 联系方式2","name":" ","qq":"111","remark":"","tel":"111"},{"email":" ","id":"0423联系方式1","name":"0423","qq":"123123","remark":"","tel":"13012312312"},{"email":" ","id":"0423联系方式2","name":"0423","qq":"123456","remark":"","tel":"13012312312"},{"email":"000","id":"1联系方式1","name":"1","qq":"0000","remark":"","tel":"100"},{"email":"1","id":"1联系方式2","name":"1","qq":"1","remark":"","tel":"1"},{"email":"1","id":"1联系方式3","name":"1","qq":"1","remark":"","tel":"11222222"},{"email":" ","id":"1联系方式4","name":"1","qq":"912378666","remark":"","tel":"183245635241"},{"email":"123456@qq.com","id":"1联系方式5","name":"1","qq":"1","remark":"","tel":"1"},{"email":" ","id":"11联系方式1","name":"11","qq":"33","remark":"","tel":"22"},{"email":" ","id":"11联系方式2","name":"11","qq":"1111","remark":"","tel":"111"},{"email":"222","id":"11联系方式3","name":"11","qq":"22","remark":"","tel":"22"},{"email":"111","id":"11联系方式4","name":"11","qq":"222","remark":"","tel":"11"},{"email":" ","id":"11联系方式5","name":"11","qq":"111","remark":"","tel":"111"},{"email":"44","id":"11联系方式6","name":"11","qq":"33","remark":"","tel":"22"},{"email":"11","id":"11联系方式7","name":"11","qq":"11","remark":"","tel":"11"},{"email":"11","id":"11联系方式8","name":"11","qq":"11","remark":"","tel":"1254488888"},{"email":" ","id":"111联系方式1","name":"111","qq":"11111","remark":"","tel":"1111"},{"email":"23","id":"111联系方式2","name":"111","qq":"11111","remark":"","tel":"1111"},{"email":" ","id":"111联系方式3","name":"111","qq":"333","remark":"","tel":"222"},{"email":"111","id":"111联系方式4","name":"111","qq":"11","remark":"","tel":"111"},{"email":"123456@qq.com","id":"111联系方式5","name":"111","qq":"123456","remark":"","tel":"13817892416"},{"email":"111","id":"111联系方式6","name":"111","qq":"111","remark":"","tel":"111"},{"email":" ","id":"111联系方式7","name":"111","qq":"000000","remark":"","tel":"15026733170"},{"email":"44444444@qq.com","id":"111联系方式8","name":"111","qq":"44444444","remark":"","tel":"12333332222"},{"email":" ","id":"111联系方式9","name":"111","qq":"111","remark":"","tel":"111"},{"email":"11","id":"111联系方式10","name":"111","qq":"11111","remark":"","tel":"1111"},{"email":"333","id":"111联系方式11","name":"111","qq":"222","remark":"","tel":"111"},{"email":"23","id":"111联系方式12","name":"111","qq":"333","remark":"","tel":"222"},{"email":" ","id":"1111联系方式1","name":"1111","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"21","id":"1212联系方式1","name":"1212","qq":"12","remark":"","tel":"12"},{"email":"11","id":"122联系方式1","name":"122","qq":"11","remark":"","tel":"111"},{"email":"212","id":"12212联系方式1","name":"12212","qq":"21","remark":"","tel":"121"},{"email":"23456","id":"1223联系方式1","name":"1223","qq":"1234","remark":"","tel":"123"},{"email":" ","id":"123联系方式1","name":"123","qq":"123","remark":"","tel":"13012312312"},{"email":"123","id":"123联系方式2","name":"123","qq":"123","remark":"","tel":"123"},{"email":"3131","id":"12312联系方式1","name":"12312","qq":"313131","remark":"","tel":"32131"},{"email":"123454657@ee.cv","id":"123123联系方式2","name":"123123","qq":"1234567","remark":"","tel":"12345678"},{"email":"545648523@qq.com","id":"12345联系方式1","name":"12345","qq":"545648523","remark":"","tel":"15263639889"},{"email":" ","id":"12356联系方式1","name":"12356","qq":"2242424","remark":"","tel":"12345789"},{"email":" ","id":"132联系方式1","name":"132","qq":"123","remark":"","tel":"13012312312"},{"email":"123456@qq.com","id":"1324联系方式2","name":"1324","qq":"123456","remark":"","tel":"123456789"},{"email":" ","id":"1324联系方式3","name":"1324","qq":"123456","remark":"","tel":"12345789"},{"email":"123456@qq.com","id":"1324联系方式4","name":"1324","qq":"123456","remark":"","tel":"12345789"},{"email":"1423132","id":"1324联系方式5","name":"1324","qq":"hjjkkjhjk","remark":"","tel":"13817892416"},{"email":"123123@ww.com","id":"1324联系方式6","name":"1324","qq":"227287","remark":"","tel":"13333333333"},{"email":" ","id":"1324联系方式7","name":"1324","qq":"2557002936","remark":"","tel":"15797692959"},{"email":"12424435@11.cn","id":"1324联系方式8","name":"1324","qq":"2242424","remark":"","tel":"244424241"},{"email":" ","id":"1324联系方式9","name":"1324","qq":"2242424","remark":"","tel":"12345789"},{"email":"12424435@11.cn","id":"1324联系方式10","name":"1324","qq":"123456","remark":"","tel":"13012313"},{"email":" ","id":"1324联系方式11","name":"1324","qq":"13132","remark":"","tel":"13213"},{"email":" ","id":"1324联系方式12","name":"1324","qq":"11111","remark":"","tel":"1111"},{"email":"12424435@11.cn","id":"1324联系方式13","name":"1324","qq":"2242424","remark":"","tel":"18062621638"},{"email":"12132@qq","id":"1324联系方式14","name":"1324","qq":"2557002936","remark":"","tel":"15797682959"},{"email":" ","id":"1324联系方式15","name":"1324","qq":"2242424","remark":"","tel":"244424241"},{"email":"123454657@ee.cv","id":"1324联系方式16","name":"1324","qq":"1234567","remark":"","tel":"12345678"},{"email":"123456@124.com","id":"1324联系方式17","name":"1324","qq":"123456789","remark":"","tel":"12345789"},{"email":"12424435@11.co","id":"1324联系方式18","name":"1324","qq":"2242424","remark":"","tel":"244424241"},{"email":"111111","id":"1324联系方式19","name":"1324","qq":"111111","remark":"","tel":"13817892416"},{"email":"2323212","id":"1324联系方式20","name":"1324","qq":"0313213131","remark":"","tel":"13817892416"},{"email":"12424435@11.cn","id":"1324联系方式21","name":"1324","qq":"2242424","remark":"","tel":"13333333333"},{"email":" ","id":"1324联系方式22","name":"1324","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"12424435@11.cn","id":"1324联系方式23","name":"1324","qq":"2242424","remark":"","tel":"1365876072"},{"email":"31431@ag","id":"1324联系方式24","name":"1324","qq":"21321321","remark":"","tel":"13564903056"},{"email":"1423132","id":"13241联系方式1","name":"13241","qq":"hjjkkjhjk","remark":"","tel":"13817892416"},{"email":"23","id":"2联系方式1","name":"2","qq":"203","remark":"","tel":"200"},{"email":"S","id":"2联系方式2","name":"2","qq":"DDD","remark":"","tel":"DD"},{"email":"123456@qq.com","id":"2017-06-25联系方式1","name":"2017-06-25","qq":"123456","remark":"","tel":"123456789"},{"email":"12424435@11.cn","id":"2017-06-25联系方式2","name":"2017-06-25","qq":"2242424","remark":"","tel":"244424241"},{"email":"123456@qq.com","id":"2017-09-14联系方式1","name":"2017-09-14","qq":"123456","remark":"","tel":"123456789"},{"email":"1222","id":"2212联系方式1","name":"2212","qq":"3333","remark":"","tel":"1222"},{"email":"222","id":"222联系方式1","name":"222","qq":"1","remark":"","tel":"22"},{"email":"55","id":"2323联系方式1","name":"2323","qq":"233","remark":"","tel":"2333"},{"email":" ","id":"2333联系方式1","name":"2333","qq":"55","remark":"","tel":"444"},{"email":" ","id":"3联系方式1","name":"3","qq":"e","remark":"","tel":"re"},{"email":"454545","id":"4545联系方式1","name":"4545","qq":"54545","remark":"","tel":"45454"},{"email":"321","id":"888联系方式1","name":"888","qq":"123","remark":"","tel":"999"},{"email":" ","id":"ZHAO联系方式1","name":"ZHAO","qq":"19401977","remark":"","tel":"61273988"},{"email":"a","id":"a联系方式1","name":"a","qq":"a","remark":"","tel":"a"},{"email":" ","id":"aa联系方式1","name":"aa","qq":"234","remark":"","tel":"123"},{"email":"17612131182@163.com","id":"apeng联系方式1","name":"apeng","qq":"303857373","remark":"","tel":"17612131182"},{"email":" ","id":"asas联系方式1","name":"asas","qq":"5656213548","remark":"","tel":"15236090690"},{"email":"adada","id":"asdad联系方式1","name":"asdad","qq":"adada","remark":"","tel":"adada"},{"email":"123456@qq.com","id":"asdfg联系方式2","name":"asdfg","qq":"123456","remark":"","tel":"123456789"},{"email":"614674","id":"asdfg联系方式3","name":"asdfg","qq":"36464973","remark":"","tel":"15900653418"},{"email":"1231321","id":"asdfg联系方式4","name":"asdfg","qq":"112265","remark":"","tel":"13817892416"},{"email":"12554","id":"asdfg联系方式5","name":"asdfg","qq":"25412","remark":"","tel":"13817892416"},{"email":"dgdg","id":"asdfg联系方式6","name":"asdfg","qq":"dfgdg","remark":"","tel":"1381789241"},{"email":"4224","id":"asdfg联系方式7","name":"asdfg","qq":"42424","remark":"","tel":"1381789241"},{"email":" ","id":"asdfg联系方式8","name":"asdfg","qq":"dgfdg","remark":"","tel":"fdgfd"},{"email":" ","id":"asdfg联系方式9","name":"asdfg","qq":"123123","remark":"","tel":"4123123"},{"email":"4254252","id":"asdfg联系方式10","name":"asdfg","qq":"452452","remark":"","tel":"13817892416"},{"email":"12424435@11.cn","id":"asdfg联系方式11","name":"asdfg","qq":"2242424","remark":"","tel":"244424241"},{"email":"123456@qq.com","id":"asdfg联系方式12","name":"asdfg","qq":"12345","remark":"","tel":"12"},{"email":"123456@qq.com","id":"asdfg联系方式13","name":"asdfg","qq":"123456","remark":"","tel":"1"},{"email":" ","id":"asdfg联系方式14","name":"asdfg","qq":"123456","remark":"","tel":"123456789"},{"email":"123456@qq.com","id":"asdfg联系方式15","name":"asdfg","qq":"123456","remark":"","tel":"12345678911"},{"email":"12345678@qq.cc","id":"asdfg联系方式16","name":"asdfg","qq":"12345678","remark":"","tel":"123456778"},{"email":"131313","id":"asdfg联系方式17","name":"asdfg","qq":"1321321","remark":"","tel":"13817892416"},{"email":"123456@qq.com","id":"asdfg联系方式18","name":"asdfg","qq":"123456","remark":"","tel":"13817892416"},{"email":"313212","id":"asdfg联系方式19","name":"asdfg","qq":"1132132","remark":"","tel":"1381789241"},{"email":"44646464654","id":"asdfg联系方式20","name":"asdfg","qq":"13212313131","remark":"","tel":"13817892416"},{"email":"45242","id":"asdfg联系方式21","name":"asdfg","qq":"45254","remark":"","tel":"13817892416"},{"email":"33333","id":"asdfg联系方式22","name":"asdfg","qq":"2222","remark":"","tel":"13918805450"},{"email":"452452","id":"asdfg联系方式23","name":"asdfg","qq":"2454524","remark":"","tel":"13817892416"},{"email":"1321123","id":"asdfg联系方式24","name":"asdfg","qq":"13213213","remark":"","tel":"13817892416"},{"email":"123456@qq.com","id":"asdfg联系方式25","name":"asdfg","qq":"123456","remark":"","tel":"15900653418"},{"email":"2654654","id":"asdfg联系方式26","name":"asdfg","qq":"266+416546","remark":"","tel":"13817892416"},{"email":"424442","id":"asdfg联系方式27","name":"asdfg","qq":"42424","remark":"","tel":"1424121"},{"email":"454545","id":"asdfg联系方式28","name":"asdfg","qq":"13817892416","remark":"","tel":"13817892416"},{"email":"564584","id":"asdfg联系方式29","name":"asdfg","qq":"13817892416","remark":"","tel":"13817892416"},{"email":"1312313","id":"asdfg联系方式30","name":"asdfg","qq":"132321321","remark":"","tel":"13817892416"},{"email":"456456","id":"asdfg联系方式31","name":"asdfg","qq":"4564564","remark":"","tel":"13817892416"},{"email":" ","id":"asdfg联系方式32","name":"asdfg","qq":"23","remark":"","tel":"12313"},{"email":"123456@qq.com","id":"asdfg联系方式33","name":"asdfg","qq":"123456","remark":"","tel":"123"},{"email":"123456@qq.com","id":"asdfg联系方式34","name":"asdfg","qq":"123456","remark":"","tel":"13333333333"},{"email":"123456@qq.com","id":"asdfg联系方式35","name":"asdfg","qq":"123456","remark":"","tel":"13000000000"},{"email":"123456@qq.com","id":"asdfg联系方式36","name":"asdfg","qq":"123456","remark":"","tel":"1333333333"},{"email":"7578575","id":"asdfg联系方式37","name":"asdfg","qq":"75785","remark":"","tel":"13817892416"},{"email":"22","id":"asdfg联系方式38","name":"asdfg","qq":"12","remark":"","tel":"13918805450"},{"email":"123456@124.com","id":"asdfg联系方式39","name":"asdfg","qq":"123456789","remark":"","tel":"12345789"},{"email":"123456@qq.com","id":"asdfg66666666666666666666666666666666联系方式1","name":"asdfg66666666666666666666666666666666","qq":"1234566666666666666666666666","remark":"","tel":"12345678966666666666666666666666666666"},{"email":"123456@qq.com","id":"asdfgf联系方式1","name":"asdfgf","qq":"123456","remark":"","tel":"123456789"},{"email":"12424435@11.cn","id":"asdsad联系方式1","name":"asdsad","qq":"2242424","remark":"","tel":"244424241"},{"email":" ","id":"asdsad联系方式2","name":"asdsad","qq":"asdasdas","remark":"","tel":"adasda"},{"email":"123456@qq.com","id":"asdsad联系方式3","name":"asdsad","qq":"123456","remark":"","tel":"123456789"},{"email":"12333","id":"cc联系方式1","name":"cc","qq":"1233","remark":"","tel":"123"},{"email":"celery.yan@newnil.com","id":"celery联系方式1","name":"celery","qq":"123456","remark":"","tel":"123456"},{"email":"123412@qq.com","id":"cxz联系方式1","name":"cxz","qq":"13412","remark":"","tel":"123413412"},{"email":"dd","id":"dd联系方式1","name":"dd","qq":"ddsd","remark":"","tel":"dd"},{"email":"d","id":"dd联系方式2","name":"dd","qq":"dd","remark":"","tel":"dsdd"},{"email":"邮箱","id":"ggg联系方式1","name":"ggg","qq":"ghghh","remark":"","tel":"dfff"},{"email":"ggdhjh","id":"ghufgh联系方式1","name":"ghufgh","qq":"111","remark":"","tel":"111"},{"email":" ","id":"hhhh联系方式1","name":"hhhh","qq":"2557002936","remark":"","tel":"15797685929"},{"email":"ooooooooooooooooooooooooo","id":"iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii联系方式1","name":"iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii","qq":"oooooooooooo","remark":"","tel":"oooooooooooooo"},{"email":"hk","id":"jlb联系方式1","name":"jlb","qq":"hik","remark":"","tel":"hil"},{"email":" ","id":"kkk联系方式1","name":"kkk","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"111111","id":"kkkk联系方式1","name":"kkkk","qq":"111111","remark":"","tel":"121111"},{"email":" ","id":"mmmm联系方式1","name":"mmmm","qq":"2557002936","remark":"","tel":"2557002936"},{"email":"11","id":"q联系方式1","name":"q","qq":"2","remark":"","tel":"1"},{"email":"123","id":"qazwsx联系方式2","name":"qazwsx","qq":"28031977328","remark":"","tel":"61156552"},{"email":"batwuq@job-mate.com","id":"qazwsx联系方式3","name":"qazwsx","qq":"28031977328","remark":"","tel":"61156552"},{"email":"mai11","id":"qian联系方式1","name":"qian","qq":"qq13","remark":"","tel":"13"},{"email":"mail11","id":"qian联系方式2","name":"qian","qq":"qq111","remark":"","tel":"135"},{"email":"123456@124.com","id":"qqq联系方式2","name":"qqq","qq":"123456789","remark":"","tel":"13659876072"},{"email":"123456@124.com","id":"qqq联系方式3","name":"qqq","qq":"123456789","remark":"","tel":"13817892416"},{"email":"123456@124.com","id":"qqq联系方式4","name":"qqq","qq":"123456789","remark":"","tel":"1234578"},{"email":"12424435@11.cn","id":"qqq联系方式5","name":"qqq","qq":"2242424","remark":"","tel":"244424241"},{"email":"123456@124.com","id":"qqq联系方式6","name":"qqq","qq":"123456789","remark":"","tel":"18062621638"},{"email":"123456@qq.com","id":"qqq联系方式7","name":"qqq","qq":"123456","remark":"","tel":"123456789"},{"email":"123456@124.com","id":"qqq联系方式8","name":"qqq","qq":"123456789","remark":"","tel":"12345789"},{"email":"r","id":"rrr联系方式1","name":"rrr","qq":"rr","remark":"","tel":"rr"},{"email":"1841444","id":"sadasdsa联系方式1","name":"sadasdsa","qq":"1183141397","remark":"","tel":"13817892416"},{"email":"12","id":"ssss联系方式1","name":"ssss","qq":"2212","remark":"","tel":"11"},{"email":"111","id":"ssss联系方式2","name":"ssss","qq":"111","remark":"","tel":"sss"},{"email":" ","id":"sunyan联系方式1","name":"sunyan","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"w","id":"w联系方式1","name":"w","qq":"w","remark":"","tel":"w"},{"email":"w1","id":"w1联系方式1","name":"w1","qq":"w1","remark":"","tel":"w1"},{"email":" ","id":"we联系方式1","name":"we","qq":"ee","remark":"","tel":"ee"},{"email":" ","id":"wode联系方式1","name":"wode","qq":"354482556","remark":"","tel":"12345789"},{"email":"wq","id":"wq联系方式1","name":"wq","qq":"wq","remark":"","tel":"wq"},{"email":"000000","id":"wqwq联系方式1","name":"wqwq","qq":"000000","remark":"","tel":"15026733170"},{"email":"ww","id":"ww联系方式1","name":"ww","qq":"ww","remark":"","tel":"ww"},{"email":"ww","id":"wwww联系方式1","name":"wwww","qq":"ww","remark":"","tel":"www"},{"email":" ","id":"yanjinhang联系方式2","name":"yanjinhang","qq":"24523452","remark":"","tel":"134135"},{"email":" ","id":"yanjinhang联系方式3","name":"yanjinhang","qq":"111","remark":"","tel":"111"},{"email":"12345678@qq.cc","id":"yanjinhang联系方式4","name":"yanjinhang","qq":"12345678","remark":"","tel":"123456778"},{"email":"12345678@qq.cc","id":"yanjinhang1联系方式1","name":"yanjinhang1","qq":"12345678","remark":"","tel":"123456778"},{"email":"235554658@qq.com","id":"yanyan联系方式1","name":"yanyan","qq":"235554658","remark":"","tel":"12569784569"},{"email":"66666666@qq.com","id":"yujhh联系方式1","name":"yujhh","qq":"11111111","remark":"","tel":"12345678999"},{"email":" ","id":"zhang联系方式1","name":"zhang","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"2557002936@qq.com","id":"zhang联系方式2","name":"zhang","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"548775632@qq.com","id":"zhang联系方式3","name":"zhang","qq":"548775632","remark":"","tel":"15569962323"},{"email":"1111110","id":"zslzsl联系方式1","name":"zslzsl","qq":"111111","remark":"","tel":"111111"},{"email":"12345678@qq.cc","id":"zxffggggghjjjj联系方式1","name":"zxffggggghjjjj","qq":"123456784","remark":"","tel":"1234567781"},{"email":"dasfas","id":"严金航联系方式1","name":"严金航","qq":"dsgadsda","remark":"","tel":"15900653418"},{"email":"dasfas","id":"严金航联系方式1联系方式1","name":"严金航联系方式1","qq":"dsgadsda","remark":"","tel":"15900653418"},{"email":"dasfas","id":"严金航联系方式1联系方式1联系方式1","name":"严金航联系方式1联系方式1","qq":"dsgadsda","remark":"","tel":"15900653418"},{"email":"996589858","id":"任联系方式1","name":"任","qq":"996589858","remark":"","tel":"13817892416"},{"email":"996589858@qq.com","id":"任联系方式2","name":"任","qq":"996589858","remark":"","tel":"13817892416"},{"email":"123456@qq.com","id":"任联系方式3","name":"任","qq":"996589858","remark":"","tel":"13817892416"},{"email":" ","id":"卡机水电费联系方式1","name":"卡机水电费","qq":"5648754","remark":"","tel":"15478954562"},{"email":"11","id":"叶斌联系方式1","name":"叶斌","qq":"2576185890","remark":"","tel":"13564456723"},{"email":"295922107@qq.com","id":"奥巴马联系方式1","name":"奥巴马","qq":"111111","remark":"","tel":"13917256155"},{"email":" ","id":"徐方敏联系方式1","name":"徐方敏","qq":"11","remark":"","tel":"15721240470"},{"email":"11","id":"徐方敏联系方式2","name":"徐方敏","qq":"11111","remark":"","tel":"15721240470"},{"email":" ","id":"测试1联系方式1","name":"测试1","qq":"测试3","remark":"","tel":"测试2"},{"email":"邮箱","id":"联系人联系方式1","name":"联系人","qq":"QQ","remark":"","tel":"联系电话"},{"email":" ","id":"请问联系方式1","name":"请问","qq":"3","remark":"","tel":"22"},{"email":" ","id":"达达达联系方式1","name":"达达达","qq":"123123","remark":"","tel":"13112312312"},{"email":"longruhong@job-mate.com","id":"龙如宏联系方式1","name":"龙如宏","qq":"2880132764","remark":"","tel":"13917256155"},{"email":"295922107@qq.com","id":"龙如宏联系方式2","name":"龙如宏","qq":"2880132764","remark":"","tel":"13917256155"},{"email":" ","id":"龙如宏联系方式3","name":"龙如宏","qq":"2880132764","remark":"","tel":"13917256155"}],"productTips":["1.需要放置24小时货物不吃泡","2.锂电池PI968/PI965无法承接","3.在目的港需要检验检疫的动植物产品不适用该产品，需按照鲜活货运价结算"],"shipper":"20131205测试客户","transferPrice":"0.00","transferPriceType":"1"}
     * orderDetail : {"bubbleRatio":{"data":"0.20","txt":"2/8"},"chargeableWeight":"200","freightPrice":"3318.00","productName":"KL-上海普货","proportion":"167","sizeNoteType":"0","unitPrice":"16.59"}
     * protectBusiness : {"content":"1","price":"1","selected":"0","valid":"0"}
     * tips : {"overdue":{"content":"","type":"0"},"paymentPay":{"content":"亲,由于贵司累计使用运费超额,此票业务为付款买单状态,待货物入库确认数据后支付此票预计费用或付清超额费用,才可安排出运,由此产生的责任由贵司承担,若有疑问请联系我司客服。","type":"1"}}
     */

    private GoodsExtensionInsuranceBean goodsExtensionInsurance;
    private OrderBillBean orderBill;
    private OrderDetailBean orderDetail;
    private ProtectBusinessBean protectBusiness;
    private TipsBean tips;
    private List<AttachServiceBean> attachService;

    public GoodsExtensionInsuranceBean getGoodsExtensionInsurance() {
        return goodsExtensionInsurance;
    }

    public void setGoodsExtensionInsurance(GoodsExtensionInsuranceBean goodsExtensionInsurance) {
        this.goodsExtensionInsurance = goodsExtensionInsurance;
    }

    public OrderBillBean getOrderBill() {
        return orderBill;
    }

    public void setOrderBill(OrderBillBean orderBill) {
        this.orderBill = orderBill;
    }

    public OrderDetailBean getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailBean orderDetail) {
        this.orderDetail = orderDetail;
    }

    public ProtectBusinessBean getProtectBusiness() {
        return protectBusiness;
    }

    public void setProtectBusiness(ProtectBusinessBean protectBusiness) {
        this.protectBusiness = protectBusiness;
    }

    public TipsBean getTips() {
        return tips;
    }

    public void setTips(TipsBean tips) {
        this.tips = tips;
    }

    public List<AttachServiceBean> getAttachService() {
        return attachService;
    }

    public void setAttachService(List<AttachServiceBean> attachService) {
        this.attachService = attachService;
    }

    public static class GoodsExtensionInsuranceBean implements Serializable{
        /**
         * planList : {"plan1":{"costRatio":"2","insuranceFee":"66.36","payoutRatio":"20"},"plan2":{"costRatio":"4","insuranceFee":"132.72","payoutRatio":"50"},"plan3":{"costRatio":"6","insuranceFee":"199.08","payoutRatio":"100"}}
         * userInfo : {"companyName":"20131205测试客户","idCard":"","realName":"","taxId":"91310105776689124Y","userType":"0"}
         * valid : 0
         */

        private PlanListBean planList;
        private UserInfoBean userInfo;
        private String valid;

        public PlanListBean getPlanList() {
            return planList;
        }

        public void setPlanList(PlanListBean planList) {
            this.planList = planList;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }

        public static class PlanListBean implements Serializable{
            /**
             * plan1 : {"costRatio":"2","insuranceFee":"66.36","payoutRatio":"20"}
             * plan2 : {"costRatio":"4","insuranceFee":"132.72","payoutRatio":"50"}
             * plan3 : {"costRatio":"6","insuranceFee":"199.08","payoutRatio":"100"}
             */

            private Plan1Bean plan1;
            private Plan2Bean plan2;
            private Plan3Bean plan3;

            public Plan1Bean getPlan1() {
                return plan1;
            }

            public void setPlan1(Plan1Bean plan1) {
                this.plan1 = plan1;
            }

            public Plan2Bean getPlan2() {
                return plan2;
            }

            public void setPlan2(Plan2Bean plan2) {
                this.plan2 = plan2;
            }

            public Plan3Bean getPlan3() {
                return plan3;
            }

            public void setPlan3(Plan3Bean plan3) {
                this.plan3 = plan3;
            }

            public static class Plan1Bean implements Serializable{
                /**
                 * costRatio : 2
                 * insuranceFee : 66.36
                 * payoutRatio : 20
                 */

                private String costRatio;
                private String insuranceFee;
                private String payoutRatio;

                public String getCostRatio() {
                    return costRatio;
                }

                public void setCostRatio(String costRatio) {
                    this.costRatio = costRatio;
                }

                public String getInsuranceFee() {
                    return insuranceFee;
                }

                public void setInsuranceFee(String insuranceFee) {
                    this.insuranceFee = insuranceFee;
                }

                public String getPayoutRatio() {
                    return payoutRatio;
                }

                public void setPayoutRatio(String payoutRatio) {
                    this.payoutRatio = payoutRatio;
                }
            }

            public static class Plan2Bean implements Serializable{
                /**
                 * costRatio : 4
                 * insuranceFee : 132.72
                 * payoutRatio : 50
                 */

                private String costRatio;
                private String insuranceFee;
                private String payoutRatio;

                public String getCostRatio() {
                    return costRatio;
                }

                public void setCostRatio(String costRatio) {
                    this.costRatio = costRatio;
                }

                public String getInsuranceFee() {
                    return insuranceFee;
                }

                public void setInsuranceFee(String insuranceFee) {
                    this.insuranceFee = insuranceFee;
                }

                public String getPayoutRatio() {
                    return payoutRatio;
                }

                public void setPayoutRatio(String payoutRatio) {
                    this.payoutRatio = payoutRatio;
                }
            }

            public static class Plan3Bean implements Serializable{
                /**
                 * costRatio : 6
                 * insuranceFee : 199.08
                 * payoutRatio : 100
                 */

                private String costRatio;
                private String insuranceFee;
                private String payoutRatio;

                public String getCostRatio() {
                    return costRatio;
                }

                public void setCostRatio(String costRatio) {
                    this.costRatio = costRatio;
                }

                public String getInsuranceFee() {
                    return insuranceFee;
                }

                public void setInsuranceFee(String insuranceFee) {
                    this.insuranceFee = insuranceFee;
                }

                public String getPayoutRatio() {
                    return payoutRatio;
                }

                public void setPayoutRatio(String payoutRatio) {
                    this.payoutRatio = payoutRatio;
                }
            }
        }

        public static class UserInfoBean implements Serializable{
            /**
             * companyName : 20131205测试客户
             * idCard :
             * realName :
             * taxId : 91310105776689124Y
             * userType : 0
             */

            private String companyName;
            private String idCard;
            private String realName;
            private String taxId;
            private String userType;

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getTaxId() {
                return taxId;
            }

            public void setTaxId(String taxId) {
                this.taxId = taxId;
            }

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }
        }
    }

    public static class OrderBillBean implements Serializable{
        /**
         * activityOff :
         * contactInfo : [{"email":"batwuq@job-mate.com","id":"qazwsx","name":"qazwsx","qq":"28031977328","remark":"","tel":"61156552"},{"email":"123456@qq.com","id":"asdfg","name":"asdfg","qq":"123456","remark":"","tel":"123456789"},{"email":"12424435@11.cn","id":"1324","name":"1324","qq":"2242424","remark":"","tel":"244424241"},{"email":"123456@124.com","id":"qqq","name":"qqq","qq":"123456789","remark":"","tel":"12345789"},{"email":"12345678@qq.cc","id":"yanjinhang","name":"yanjinhang","qq":"12345678","remark":"","tel":"123456778"},{"email":"123454657@ee.cv","id":"123123","name":"123123","qq":"1234567","remark":"","tel":"12345678"},{"email":" ","id":" 联系方式1","name":" ","qq":" ","remark":"","tel":" "},{"email":" ","id":" 联系方式2","name":" ","qq":"111","remark":"","tel":"111"},{"email":" ","id":"0423联系方式1","name":"0423","qq":"123123","remark":"","tel":"13012312312"},{"email":" ","id":"0423联系方式2","name":"0423","qq":"123456","remark":"","tel":"13012312312"},{"email":"000","id":"1联系方式1","name":"1","qq":"0000","remark":"","tel":"100"},{"email":"1","id":"1联系方式2","name":"1","qq":"1","remark":"","tel":"1"},{"email":"1","id":"1联系方式3","name":"1","qq":"1","remark":"","tel":"11222222"},{"email":" ","id":"1联系方式4","name":"1","qq":"912378666","remark":"","tel":"183245635241"},{"email":"123456@qq.com","id":"1联系方式5","name":"1","qq":"1","remark":"","tel":"1"},{"email":" ","id":"11联系方式1","name":"11","qq":"33","remark":"","tel":"22"},{"email":" ","id":"11联系方式2","name":"11","qq":"1111","remark":"","tel":"111"},{"email":"222","id":"11联系方式3","name":"11","qq":"22","remark":"","tel":"22"},{"email":"111","id":"11联系方式4","name":"11","qq":"222","remark":"","tel":"11"},{"email":" ","id":"11联系方式5","name":"11","qq":"111","remark":"","tel":"111"},{"email":"44","id":"11联系方式6","name":"11","qq":"33","remark":"","tel":"22"},{"email":"11","id":"11联系方式7","name":"11","qq":"11","remark":"","tel":"11"},{"email":"11","id":"11联系方式8","name":"11","qq":"11","remark":"","tel":"1254488888"},{"email":" ","id":"111联系方式1","name":"111","qq":"11111","remark":"","tel":"1111"},{"email":"23","id":"111联系方式2","name":"111","qq":"11111","remark":"","tel":"1111"},{"email":" ","id":"111联系方式3","name":"111","qq":"333","remark":"","tel":"222"},{"email":"111","id":"111联系方式4","name":"111","qq":"11","remark":"","tel":"111"},{"email":"123456@qq.com","id":"111联系方式5","name":"111","qq":"123456","remark":"","tel":"13817892416"},{"email":"111","id":"111联系方式6","name":"111","qq":"111","remark":"","tel":"111"},{"email":" ","id":"111联系方式7","name":"111","qq":"000000","remark":"","tel":"15026733170"},{"email":"44444444@qq.com","id":"111联系方式8","name":"111","qq":"44444444","remark":"","tel":"12333332222"},{"email":" ","id":"111联系方式9","name":"111","qq":"111","remark":"","tel":"111"},{"email":"11","id":"111联系方式10","name":"111","qq":"11111","remark":"","tel":"1111"},{"email":"333","id":"111联系方式11","name":"111","qq":"222","remark":"","tel":"111"},{"email":"23","id":"111联系方式12","name":"111","qq":"333","remark":"","tel":"222"},{"email":" ","id":"1111联系方式1","name":"1111","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"21","id":"1212联系方式1","name":"1212","qq":"12","remark":"","tel":"12"},{"email":"11","id":"122联系方式1","name":"122","qq":"11","remark":"","tel":"111"},{"email":"212","id":"12212联系方式1","name":"12212","qq":"21","remark":"","tel":"121"},{"email":"23456","id":"1223联系方式1","name":"1223","qq":"1234","remark":"","tel":"123"},{"email":" ","id":"123联系方式1","name":"123","qq":"123","remark":"","tel":"13012312312"},{"email":"123","id":"123联系方式2","name":"123","qq":"123","remark":"","tel":"123"},{"email":"3131","id":"12312联系方式1","name":"12312","qq":"313131","remark":"","tel":"32131"},{"email":"123454657@ee.cv","id":"123123联系方式2","name":"123123","qq":"1234567","remark":"","tel":"12345678"},{"email":"545648523@qq.com","id":"12345联系方式1","name":"12345","qq":"545648523","remark":"","tel":"15263639889"},{"email":" ","id":"12356联系方式1","name":"12356","qq":"2242424","remark":"","tel":"12345789"},{"email":" ","id":"132联系方式1","name":"132","qq":"123","remark":"","tel":"13012312312"},{"email":"123456@qq.com","id":"1324联系方式2","name":"1324","qq":"123456","remark":"","tel":"123456789"},{"email":" ","id":"1324联系方式3","name":"1324","qq":"123456","remark":"","tel":"12345789"},{"email":"123456@qq.com","id":"1324联系方式4","name":"1324","qq":"123456","remark":"","tel":"12345789"},{"email":"1423132","id":"1324联系方式5","name":"1324","qq":"hjjkkjhjk","remark":"","tel":"13817892416"},{"email":"123123@ww.com","id":"1324联系方式6","name":"1324","qq":"227287","remark":"","tel":"13333333333"},{"email":" ","id":"1324联系方式7","name":"1324","qq":"2557002936","remark":"","tel":"15797692959"},{"email":"12424435@11.cn","id":"1324联系方式8","name":"1324","qq":"2242424","remark":"","tel":"244424241"},{"email":" ","id":"1324联系方式9","name":"1324","qq":"2242424","remark":"","tel":"12345789"},{"email":"12424435@11.cn","id":"1324联系方式10","name":"1324","qq":"123456","remark":"","tel":"13012313"},{"email":" ","id":"1324联系方式11","name":"1324","qq":"13132","remark":"","tel":"13213"},{"email":" ","id":"1324联系方式12","name":"1324","qq":"11111","remark":"","tel":"1111"},{"email":"12424435@11.cn","id":"1324联系方式13","name":"1324","qq":"2242424","remark":"","tel":"18062621638"},{"email":"12132@qq","id":"1324联系方式14","name":"1324","qq":"2557002936","remark":"","tel":"15797682959"},{"email":" ","id":"1324联系方式15","name":"1324","qq":"2242424","remark":"","tel":"244424241"},{"email":"123454657@ee.cv","id":"1324联系方式16","name":"1324","qq":"1234567","remark":"","tel":"12345678"},{"email":"123456@124.com","id":"1324联系方式17","name":"1324","qq":"123456789","remark":"","tel":"12345789"},{"email":"12424435@11.co","id":"1324联系方式18","name":"1324","qq":"2242424","remark":"","tel":"244424241"},{"email":"111111","id":"1324联系方式19","name":"1324","qq":"111111","remark":"","tel":"13817892416"},{"email":"2323212","id":"1324联系方式20","name":"1324","qq":"0313213131","remark":"","tel":"13817892416"},{"email":"12424435@11.cn","id":"1324联系方式21","name":"1324","qq":"2242424","remark":"","tel":"13333333333"},{"email":" ","id":"1324联系方式22","name":"1324","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"12424435@11.cn","id":"1324联系方式23","name":"1324","qq":"2242424","remark":"","tel":"1365876072"},{"email":"31431@ag","id":"1324联系方式24","name":"1324","qq":"21321321","remark":"","tel":"13564903056"},{"email":"1423132","id":"13241联系方式1","name":"13241","qq":"hjjkkjhjk","remark":"","tel":"13817892416"},{"email":"23","id":"2联系方式1","name":"2","qq":"203","remark":"","tel":"200"},{"email":"S","id":"2联系方式2","name":"2","qq":"DDD","remark":"","tel":"DD"},{"email":"123456@qq.com","id":"2017-06-25联系方式1","name":"2017-06-25","qq":"123456","remark":"","tel":"123456789"},{"email":"12424435@11.cn","id":"2017-06-25联系方式2","name":"2017-06-25","qq":"2242424","remark":"","tel":"244424241"},{"email":"123456@qq.com","id":"2017-09-14联系方式1","name":"2017-09-14","qq":"123456","remark":"","tel":"123456789"},{"email":"1222","id":"2212联系方式1","name":"2212","qq":"3333","remark":"","tel":"1222"},{"email":"222","id":"222联系方式1","name":"222","qq":"1","remark":"","tel":"22"},{"email":"55","id":"2323联系方式1","name":"2323","qq":"233","remark":"","tel":"2333"},{"email":" ","id":"2333联系方式1","name":"2333","qq":"55","remark":"","tel":"444"},{"email":" ","id":"3联系方式1","name":"3","qq":"e","remark":"","tel":"re"},{"email":"454545","id":"4545联系方式1","name":"4545","qq":"54545","remark":"","tel":"45454"},{"email":"321","id":"888联系方式1","name":"888","qq":"123","remark":"","tel":"999"},{"email":" ","id":"ZHAO联系方式1","name":"ZHAO","qq":"19401977","remark":"","tel":"61273988"},{"email":"a","id":"a联系方式1","name":"a","qq":"a","remark":"","tel":"a"},{"email":" ","id":"aa联系方式1","name":"aa","qq":"234","remark":"","tel":"123"},{"email":"17612131182@163.com","id":"apeng联系方式1","name":"apeng","qq":"303857373","remark":"","tel":"17612131182"},{"email":" ","id":"asas联系方式1","name":"asas","qq":"5656213548","remark":"","tel":"15236090690"},{"email":"adada","id":"asdad联系方式1","name":"asdad","qq":"adada","remark":"","tel":"adada"},{"email":"123456@qq.com","id":"asdfg联系方式2","name":"asdfg","qq":"123456","remark":"","tel":"123456789"},{"email":"614674","id":"asdfg联系方式3","name":"asdfg","qq":"36464973","remark":"","tel":"15900653418"},{"email":"1231321","id":"asdfg联系方式4","name":"asdfg","qq":"112265","remark":"","tel":"13817892416"},{"email":"12554","id":"asdfg联系方式5","name":"asdfg","qq":"25412","remark":"","tel":"13817892416"},{"email":"dgdg","id":"asdfg联系方式6","name":"asdfg","qq":"dfgdg","remark":"","tel":"1381789241"},{"email":"4224","id":"asdfg联系方式7","name":"asdfg","qq":"42424","remark":"","tel":"1381789241"},{"email":" ","id":"asdfg联系方式8","name":"asdfg","qq":"dgfdg","remark":"","tel":"fdgfd"},{"email":" ","id":"asdfg联系方式9","name":"asdfg","qq":"123123","remark":"","tel":"4123123"},{"email":"4254252","id":"asdfg联系方式10","name":"asdfg","qq":"452452","remark":"","tel":"13817892416"},{"email":"12424435@11.cn","id":"asdfg联系方式11","name":"asdfg","qq":"2242424","remark":"","tel":"244424241"},{"email":"123456@qq.com","id":"asdfg联系方式12","name":"asdfg","qq":"12345","remark":"","tel":"12"},{"email":"123456@qq.com","id":"asdfg联系方式13","name":"asdfg","qq":"123456","remark":"","tel":"1"},{"email":" ","id":"asdfg联系方式14","name":"asdfg","qq":"123456","remark":"","tel":"123456789"},{"email":"123456@qq.com","id":"asdfg联系方式15","name":"asdfg","qq":"123456","remark":"","tel":"12345678911"},{"email":"12345678@qq.cc","id":"asdfg联系方式16","name":"asdfg","qq":"12345678","remark":"","tel":"123456778"},{"email":"131313","id":"asdfg联系方式17","name":"asdfg","qq":"1321321","remark":"","tel":"13817892416"},{"email":"123456@qq.com","id":"asdfg联系方式18","name":"asdfg","qq":"123456","remark":"","tel":"13817892416"},{"email":"313212","id":"asdfg联系方式19","name":"asdfg","qq":"1132132","remark":"","tel":"1381789241"},{"email":"44646464654","id":"asdfg联系方式20","name":"asdfg","qq":"13212313131","remark":"","tel":"13817892416"},{"email":"45242","id":"asdfg联系方式21","name":"asdfg","qq":"45254","remark":"","tel":"13817892416"},{"email":"33333","id":"asdfg联系方式22","name":"asdfg","qq":"2222","remark":"","tel":"13918805450"},{"email":"452452","id":"asdfg联系方式23","name":"asdfg","qq":"2454524","remark":"","tel":"13817892416"},{"email":"1321123","id":"asdfg联系方式24","name":"asdfg","qq":"13213213","remark":"","tel":"13817892416"},{"email":"123456@qq.com","id":"asdfg联系方式25","name":"asdfg","qq":"123456","remark":"","tel":"15900653418"},{"email":"2654654","id":"asdfg联系方式26","name":"asdfg","qq":"266+416546","remark":"","tel":"13817892416"},{"email":"424442","id":"asdfg联系方式27","name":"asdfg","qq":"42424","remark":"","tel":"1424121"},{"email":"454545","id":"asdfg联系方式28","name":"asdfg","qq":"13817892416","remark":"","tel":"13817892416"},{"email":"564584","id":"asdfg联系方式29","name":"asdfg","qq":"13817892416","remark":"","tel":"13817892416"},{"email":"1312313","id":"asdfg联系方式30","name":"asdfg","qq":"132321321","remark":"","tel":"13817892416"},{"email":"456456","id":"asdfg联系方式31","name":"asdfg","qq":"4564564","remark":"","tel":"13817892416"},{"email":" ","id":"asdfg联系方式32","name":"asdfg","qq":"23","remark":"","tel":"12313"},{"email":"123456@qq.com","id":"asdfg联系方式33","name":"asdfg","qq":"123456","remark":"","tel":"123"},{"email":"123456@qq.com","id":"asdfg联系方式34","name":"asdfg","qq":"123456","remark":"","tel":"13333333333"},{"email":"123456@qq.com","id":"asdfg联系方式35","name":"asdfg","qq":"123456","remark":"","tel":"13000000000"},{"email":"123456@qq.com","id":"asdfg联系方式36","name":"asdfg","qq":"123456","remark":"","tel":"1333333333"},{"email":"7578575","id":"asdfg联系方式37","name":"asdfg","qq":"75785","remark":"","tel":"13817892416"},{"email":"22","id":"asdfg联系方式38","name":"asdfg","qq":"12","remark":"","tel":"13918805450"},{"email":"123456@124.com","id":"asdfg联系方式39","name":"asdfg","qq":"123456789","remark":"","tel":"12345789"},{"email":"123456@qq.com","id":"asdfg66666666666666666666666666666666联系方式1","name":"asdfg66666666666666666666666666666666","qq":"1234566666666666666666666666","remark":"","tel":"12345678966666666666666666666666666666"},{"email":"123456@qq.com","id":"asdfgf联系方式1","name":"asdfgf","qq":"123456","remark":"","tel":"123456789"},{"email":"12424435@11.cn","id":"asdsad联系方式1","name":"asdsad","qq":"2242424","remark":"","tel":"244424241"},{"email":" ","id":"asdsad联系方式2","name":"asdsad","qq":"asdasdas","remark":"","tel":"adasda"},{"email":"123456@qq.com","id":"asdsad联系方式3","name":"asdsad","qq":"123456","remark":"","tel":"123456789"},{"email":"12333","id":"cc联系方式1","name":"cc","qq":"1233","remark":"","tel":"123"},{"email":"celery.yan@newnil.com","id":"celery联系方式1","name":"celery","qq":"123456","remark":"","tel":"123456"},{"email":"123412@qq.com","id":"cxz联系方式1","name":"cxz","qq":"13412","remark":"","tel":"123413412"},{"email":"dd","id":"dd联系方式1","name":"dd","qq":"ddsd","remark":"","tel":"dd"},{"email":"d","id":"dd联系方式2","name":"dd","qq":"dd","remark":"","tel":"dsdd"},{"email":"邮箱","id":"ggg联系方式1","name":"ggg","qq":"ghghh","remark":"","tel":"dfff"},{"email":"ggdhjh","id":"ghufgh联系方式1","name":"ghufgh","qq":"111","remark":"","tel":"111"},{"email":" ","id":"hhhh联系方式1","name":"hhhh","qq":"2557002936","remark":"","tel":"15797685929"},{"email":"ooooooooooooooooooooooooo","id":"iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii联系方式1","name":"iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii","qq":"oooooooooooo","remark":"","tel":"oooooooooooooo"},{"email":"hk","id":"jlb联系方式1","name":"jlb","qq":"hik","remark":"","tel":"hil"},{"email":" ","id":"kkk联系方式1","name":"kkk","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"111111","id":"kkkk联系方式1","name":"kkkk","qq":"111111","remark":"","tel":"121111"},{"email":" ","id":"mmmm联系方式1","name":"mmmm","qq":"2557002936","remark":"","tel":"2557002936"},{"email":"11","id":"q联系方式1","name":"q","qq":"2","remark":"","tel":"1"},{"email":"123","id":"qazwsx联系方式2","name":"qazwsx","qq":"28031977328","remark":"","tel":"61156552"},{"email":"batwuq@job-mate.com","id":"qazwsx联系方式3","name":"qazwsx","qq":"28031977328","remark":"","tel":"61156552"},{"email":"mai11","id":"qian联系方式1","name":"qian","qq":"qq13","remark":"","tel":"13"},{"email":"mail11","id":"qian联系方式2","name":"qian","qq":"qq111","remark":"","tel":"135"},{"email":"123456@124.com","id":"qqq联系方式2","name":"qqq","qq":"123456789","remark":"","tel":"13659876072"},{"email":"123456@124.com","id":"qqq联系方式3","name":"qqq","qq":"123456789","remark":"","tel":"13817892416"},{"email":"123456@124.com","id":"qqq联系方式4","name":"qqq","qq":"123456789","remark":"","tel":"1234578"},{"email":"12424435@11.cn","id":"qqq联系方式5","name":"qqq","qq":"2242424","remark":"","tel":"244424241"},{"email":"123456@124.com","id":"qqq联系方式6","name":"qqq","qq":"123456789","remark":"","tel":"18062621638"},{"email":"123456@qq.com","id":"qqq联系方式7","name":"qqq","qq":"123456","remark":"","tel":"123456789"},{"email":"123456@124.com","id":"qqq联系方式8","name":"qqq","qq":"123456789","remark":"","tel":"12345789"},{"email":"r","id":"rrr联系方式1","name":"rrr","qq":"rr","remark":"","tel":"rr"},{"email":"1841444","id":"sadasdsa联系方式1","name":"sadasdsa","qq":"1183141397","remark":"","tel":"13817892416"},{"email":"12","id":"ssss联系方式1","name":"ssss","qq":"2212","remark":"","tel":"11"},{"email":"111","id":"ssss联系方式2","name":"ssss","qq":"111","remark":"","tel":"sss"},{"email":" ","id":"sunyan联系方式1","name":"sunyan","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"w","id":"w联系方式1","name":"w","qq":"w","remark":"","tel":"w"},{"email":"w1","id":"w1联系方式1","name":"w1","qq":"w1","remark":"","tel":"w1"},{"email":" ","id":"we联系方式1","name":"we","qq":"ee","remark":"","tel":"ee"},{"email":" ","id":"wode联系方式1","name":"wode","qq":"354482556","remark":"","tel":"12345789"},{"email":"wq","id":"wq联系方式1","name":"wq","qq":"wq","remark":"","tel":"wq"},{"email":"000000","id":"wqwq联系方式1","name":"wqwq","qq":"000000","remark":"","tel":"15026733170"},{"email":"ww","id":"ww联系方式1","name":"ww","qq":"ww","remark":"","tel":"ww"},{"email":"ww","id":"wwww联系方式1","name":"wwww","qq":"ww","remark":"","tel":"www"},{"email":" ","id":"yanjinhang联系方式2","name":"yanjinhang","qq":"24523452","remark":"","tel":"134135"},{"email":" ","id":"yanjinhang联系方式3","name":"yanjinhang","qq":"111","remark":"","tel":"111"},{"email":"12345678@qq.cc","id":"yanjinhang联系方式4","name":"yanjinhang","qq":"12345678","remark":"","tel":"123456778"},{"email":"12345678@qq.cc","id":"yanjinhang1联系方式1","name":"yanjinhang1","qq":"12345678","remark":"","tel":"123456778"},{"email":"235554658@qq.com","id":"yanyan联系方式1","name":"yanyan","qq":"235554658","remark":"","tel":"12569784569"},{"email":"66666666@qq.com","id":"yujhh联系方式1","name":"yujhh","qq":"11111111","remark":"","tel":"12345678999"},{"email":" ","id":"zhang联系方式1","name":"zhang","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"2557002936@qq.com","id":"zhang联系方式2","name":"zhang","qq":"2557002936","remark":"","tel":"15797682959"},{"email":"548775632@qq.com","id":"zhang联系方式3","name":"zhang","qq":"548775632","remark":"","tel":"15569962323"},{"email":"1111110","id":"zslzsl联系方式1","name":"zslzsl","qq":"111111","remark":"","tel":"111111"},{"email":"12345678@qq.cc","id":"zxffggggghjjjj联系方式1","name":"zxffggggghjjjj","qq":"123456784","remark":"","tel":"1234567781"},{"email":"dasfas","id":"严金航联系方式1","name":"严金航","qq":"dsgadsda","remark":"","tel":"15900653418"},{"email":"dasfas","id":"严金航联系方式1联系方式1","name":"严金航联系方式1","qq":"dsgadsda","remark":"","tel":"15900653418"},{"email":"dasfas","id":"严金航联系方式1联系方式1联系方式1","name":"严金航联系方式1联系方式1","qq":"dsgadsda","remark":"","tel":"15900653418"},{"email":"996589858","id":"任联系方式1","name":"任","qq":"996589858","remark":"","tel":"13817892416"},{"email":"996589858@qq.com","id":"任联系方式2","name":"任","qq":"996589858","remark":"","tel":"13817892416"},{"email":"123456@qq.com","id":"任联系方式3","name":"任","qq":"996589858","remark":"","tel":"13817892416"},{"email":" ","id":"卡机水电费联系方式1","name":"卡机水电费","qq":"5648754","remark":"","tel":"15478954562"},{"email":"11","id":"叶斌联系方式1","name":"叶斌","qq":"2576185890","remark":"","tel":"13564456723"},{"email":"295922107@qq.com","id":"奥巴马联系方式1","name":"奥巴马","qq":"111111","remark":"","tel":"13917256155"},{"email":" ","id":"徐方敏联系方式1","name":"徐方敏","qq":"11","remark":"","tel":"15721240470"},{"email":"11","id":"徐方敏联系方式2","name":"徐方敏","qq":"11111","remark":"","tel":"15721240470"},{"email":" ","id":"测试1联系方式1","name":"测试1","qq":"测试3","remark":"","tel":"测试2"},{"email":"邮箱","id":"联系人联系方式1","name":"联系人","qq":"QQ","remark":"","tel":"联系电话"},{"email":" ","id":"请问联系方式1","name":"请问","qq":"3","remark":"","tel":"22"},{"email":" ","id":"达达达联系方式1","name":"达达达","qq":"123123","remark":"","tel":"13112312312"},{"email":"longruhong@job-mate.com","id":"龙如宏联系方式1","name":"龙如宏","qq":"2880132764","remark":"","tel":"13917256155"},{"email":"295922107@qq.com","id":"龙如宏联系方式2","name":"龙如宏","qq":"2880132764","remark":"","tel":"13917256155"},{"email":" ","id":"龙如宏联系方式3","name":"龙如宏","qq":"2880132764","remark":"","tel":"13917256155"}]
         * productTips : ["1.需要放置24小时货物不吃泡","2.锂电池PI968/PI965无法承接","3.在目的港需要检验检疫的动植物产品不适用该产品，需按照鲜活货运价结算"]
         * shipper : 20131205测试客户
         * transferPrice : 0.00
         * transferPriceType : 1
         */

        private String activityOff;
        private String shipper;
        private String transferPrice;
        private String transferPriceType;
        private List<ContactInfoBean> contactInfo;
        private List<String> productTips;

        public String getActivityOff() {
            return activityOff;
        }

        public void setActivityOff(String activityOff) {
            this.activityOff = activityOff;
        }

        public String getShipper() {
            return shipper;
        }

        public void setShipper(String shipper) {
            this.shipper = shipper;
        }

        public String getTransferPrice() {
            return transferPrice;
        }

        public void setTransferPrice(String transferPrice) {
            this.transferPrice = transferPrice;
        }

        public String getTransferPriceType() {
            return transferPriceType;
        }

        public void setTransferPriceType(String transferPriceType) {
            this.transferPriceType = transferPriceType;
        }

        public List<ContactInfoBean> getContactInfo() {
            return contactInfo;
        }

        public void setContactInfo(List<ContactInfoBean> contactInfo) {
            this.contactInfo = contactInfo;
        }

        public List<String> getProductTips() {
            return productTips;
        }

        public void setProductTips(List<String> productTips) {
            this.productTips = productTips;
        }

        public static class ContactInfoBean implements Serializable{
            /**
             * email : batwuq@job-mate.com
             * id : qazwsx
             * name : qazwsx
             * qq : 28031977328
             * remark :
             * tel : 61156552
             */

            private String email;
            private String id;
            private String name;
            private String qq;
            private String remark;
            private String tel;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }
        }
    }

    public static class OrderDetailBean implements Serializable{
        /**
         * bubbleRatio : {"data":"0.20","txt":"2/8"}
         * chargeableWeight : 200
         * freightPrice : 3318.00
         * productName : KL-上海普货
         * proportion : 167
         * sizeNoteType : 0
         * unitPrice : 16.59
         */

        private BubbleRatioBean bubbleRatio;
        private String chargeableWeight;
        private String freightPrice;
        private String productName;
        private String proportion;
        private String sizeNoteType;
        private String unitPrice;

        public BubbleRatioBean getBubbleRatio() {
            return bubbleRatio;
        }

        public void setBubbleRatio(BubbleRatioBean bubbleRatio) {
            this.bubbleRatio = bubbleRatio;
        }

        public String getChargeableWeight() {
            return chargeableWeight;
        }

        public void setChargeableWeight(String chargeableWeight) {
            this.chargeableWeight = chargeableWeight;
        }

        public String getFreightPrice() {
            return freightPrice;
        }

        public void setFreightPrice(String freightPrice) {
            this.freightPrice = freightPrice;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProportion() {
            return proportion;
        }

        public void setProportion(String proportion) {
            this.proportion = proportion;
        }

        public String getSizeNoteType() {
            return sizeNoteType;
        }

        public void setSizeNoteType(String sizeNoteType) {
            this.sizeNoteType = sizeNoteType;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }

        public static class BubbleRatioBean implements Serializable{
            /**
             * data : 0.20
             * txt : 2/8
             */

            private String data;
            private String txt;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }

    public static class ProtectBusinessBean implements Serializable{
        /**
         * content : 1
         * price : 1
         * selected : 0
         * valid : 0
         */

        private String content;
        private String price;
        private String selected;
        private String valid;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSelected() {
            return selected;
        }

        public void setSelected(String selected) {
            this.selected = selected;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }
    }

    public static class TipsBean implements Serializable{
        /**
         * overdue : {"content":"","type":"0"}
         * paymentPay : {"content":"亲,由于贵司累计使用运费超额,此票业务为付款买单状态,待货物入库确认数据后支付此票预计费用或付清超额费用,才可安排出运,由此产生的责任由贵司承担,若有疑问请联系我司客服。","type":"1"}
         */

        private OverdueBean overdue;
        private PaymentPayBean paymentPay;

        public OverdueBean getOverdue() {
            return overdue;
        }

        public void setOverdue(OverdueBean overdue) {
            this.overdue = overdue;
        }

        public PaymentPayBean getPaymentPay() {
            return paymentPay;
        }

        public void setPaymentPay(PaymentPayBean paymentPay) {
            this.paymentPay = paymentPay;
        }

        public static class OverdueBean {
            /**
             * content :
             * type : 0
             */

            private String content;
            private String type;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class PaymentPayBean implements Serializable{
            /**
             * content : 亲,由于贵司累计使用运费超额,此票业务为付款买单状态,待货物入库确认数据后支付此票预计费用或付清超额费用,才可安排出运,由此产生的责任由贵司承担,若有疑问请联系我司客服。
             * type : 1
             */

            private String content;
            private String type;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }

    public static class AttachServiceBean implements Serializable{
        /**
         * content : 100
         * id : <p>报关：100</p>
         * name : 报关
         * price : 100
         * selected : 1
         * valid : 1
         */

        private String content;
        private String id;
        private String name;
        private String price;
        private String selected;
        private String valid;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSelected() {
            return selected;
        }

        public void setSelected(String selected) {
            this.selected = selected;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }
    }
}
