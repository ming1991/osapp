package net.oschina.app.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/4 19:04
 * 描述：    TODO
 */



@XStreamAlias("oschina")
public class TestBean {

    /**
     * catalog : 1
     * pagesize : 20
     * newsCount : 0
     * newslist : {"news":[{"author":"淡漠悠然","id":78722,"title":"Spring Data Commons 1.12.5 发布","body":"Spring Data Commons 1.12.5 发布了，Spring Data 项目的目的是为了简化...","authorid":2305107,"pubDate":"2016-11-04 18:29:43","url":"","commentCount":0,"newstype":{"eventurl":"","type":0,"authoruid2":2305107}},{"author":"狮子的魂","id":78720,"title":"Jcseg 2.0.0 发布，自定义词库开发支持优化","body":"    Jcseg是基于mmseg算法的一个轻量级中文分词器，同时集成了关键字提...","authorid":853816,"pubDate":"2016-11-04 11:06:30","url":"","commentCount":8,"newstype":{"eventurl":"","type":0,"authoruid2":853816}},{"author":"王练","id":78719,"title":"美国政府推出 Code.gov 平台，提供联邦源代码","body":"美国联邦政府今年8月公布了联邦源码政策（PDF），要求使用联邦政府资金开...","authorid":2896879,"pubDate":"2016-11-04 11:01:51","url":"","commentCount":17,"newstype":{"eventurl":"","type":0,"authoruid2":2896879}},{"author":"leolovenet","id":78717,"title":"Vagrant 1.8.6 发布，虚拟化开发环境管理","body":"Vagrant 1.8.6 发布了，Vagrant 是一个基于 Ruby 的工具，用于创建和部署...","authorid":110578,"pubDate":"2016-11-04 10:16:45","url":"","commentCount":7,"newstype":{"eventurl":"","type":0,"authoruid2":110578}},{"author":"强子哥哥","id":78716,"title":"MyThrift V0.4 正式发布(轻量级微服务框架)","body":"MyThrift是基于Facebook thrift 0.9.3 基础上开发的轻量级微服务框架， ...","authorid":1382024,"pubDate":"2016-11-04 09:15:39","url":"","commentCount":10,"newstype":{"eventurl":"","type":0,"authoruid2":1382024}},{"author":"达尔文","id":78715,"title":"Red Hat Enterprise Linux 7.3 发布下载","body":"红帽已宣布可以对公司的Enterprise Linux系列产品进行全新升级。新版本（...","authorid":2903254,"pubDate":"2016-11-04 08:15:10","url":"","commentCount":27,"newstype":{"eventurl":"","type":0,"authoruid2":2903254}},{"author":"王练","id":78714,"title":"12月国内首个 Apache 基金会项目在北京源创会分享","body":"12月4日OSC源创会年终盛典相约北京国际会议中心。源创会年终盛典是由\u201c开...","authorid":2896879,"pubDate":"2016-11-04 08:05:53","url":"http://www.oschina.net/2016-beijing-ceremony","commentCount":0,"newstype":{"eventurl":"","type":0,"authoruid2":2896879}},{"author":"王练","id":78713,"title":"OSChina 周五乱弹\u2014\u2014赖床是对冬季最起码的尊重","body":"天气变凉了，完全没预兆啊，每天早晨起床前，我都在认真的思索这个问题。...","authorid":2896879,"pubDate":"2016-11-04 08:01:11","url":"https://my.oschina.net/xxiaobian/blog/781162","commentCount":47,"newstype":{"eventurl":"","attachment":781162,"type":3,"authoruid2":1428332}},{"author":"王练","id":78712,"title":"每日一博 | 工作中常用到的 Java 反射","body":"这次提到的Java反射涉及的代码比较多。因为工作中经常用到反射，对代码做...","authorid":2896879,"pubDate":"2016-11-04 07:59:01","url":"https://my.oschina.net/eliyanfei/blog/780170","commentCount":5,"newstype":{"eventurl":"","attachment":780170,"type":3,"authoruid2":127344}},{"author":"达尔文","id":78711,"title":"Ubuntu Core 16 发布，为安全物联网而生 ","body":"Canonical 今日发布了  Ubuntu Core 16，除安全方面的更新外，还公布了...","authorid":2903254,"pubDate":"2016-11-04 07:55:53","url":"","commentCount":4,"newstype":{"eventurl":"","type":0,"authoruid2":2903254}},{"author":"王练","id":10003619,"title":"协作翻译 | 8分钟看懂 Java 中的浅拷贝与深拷贝","body":"针对深拷贝和浅拷贝的不同之处进行讨论，助你快速了解。Java 中的拷贝可...","authorid":1,"pubDate":"2016-11-04 07:52:11","url":"","commentCount":12,"newstype":{"eventurl":"","type":0,"authoruid2":1}},{"author":"达尔文","id":78709,"title":"Visual Studio Code 1.7.1 恢复发布","body":"Visual Studio Code 1.7.1 发布啦！ Visual Studio Code 是一个运行于 ...","authorid":2903254,"pubDate":"2016-11-04 07:51:59","url":"","commentCount":25,"newstype":{"eventurl":"","type":0,"authoruid2":2903254}},{"author":"王练","id":78708,"title":"开源访谈 | 覃俊文:做 Web APP 你应该知道这些","body":"最近几年，随着前后端分离、单页面应用的崛起，网页正变得越来越应用化。...","authorid":2896879,"pubDate":"2016-11-04 07:49:08","url":"https://www.oschina.net/question/2896879_2204590","commentCount":8,"newstype":{"eventurl":"","attachment":2204590,"type":2,"authoruid2":2896879}},{"author":"王练","id":78707,"title":"这些\u201c古老\u201d的编程语言，你还在使用吗？","body":"编程语言似乎永远不会真正消失，最多只是\u201c淡入淡出\u201d。有些很早出现的语...","authorid":2896879,"pubDate":"2016-11-04 07:45:54","url":"","commentCount":28,"newstype":{"eventurl":"","type":0,"authoruid2":2896879}},{"author":"王练","id":78706,"title":"Git 项目推荐 | 基于Java 生成的 Intellij 插件 pngen","body":"pngen是根据Java实体类生成建表语句的Intellij插件，下载pngen.jar到本地...","authorid":2896879,"pubDate":"2016-11-04 07:43:03","url":"https://git.oschina.net/piaoniu/pngen","commentCount":0,"newstype":{"eventurl":"","type":0,"authoruid2":2896879}},{"author":"Kerbores","id":78705,"title":"NUTZ-ONEKEY 2.1 发布，修复360代码检测","body":"NUTZ-ONEKEY 2.1 发布，NUTZ-ONEKEY是NUTZ一键脚手架。 修复360代码检测...","authorid":1427223,"pubDate":"2016-11-04 07:43:01","url":"","commentCount":5,"newstype":{"eventurl":"","type":0,"authoruid2":1427223}},{"author":"达尔文","id":78704,"title":"编程语言 Scala 2.12.0 正式版发布","body":"编程语言 Scala 2.12.0 正式版发布了。 Scala 2.12.0编译器已经完全翻修...","authorid":2903254,"pubDate":"2016-11-04 07:42:20","url":"","commentCount":10,"newstype":{"eventurl":"","type":0,"authoruid2":2903254}},{"author":"王练","id":78703,"title":"VimDesktop \u2014\u2014 VIM 化桌面程序","body":"VimDesktop 是一套基于 AHK 编辑的热键管理框架,通过Autohotkey来VIM化桌...","authorid":2896879,"pubDate":"2016-11-04 07:40:14","url":"https://www.oschina.net/p/vim-desktop","commentCount":2,"newstype":{"eventurl":"","attachment":"vim-desktop","type":1,"authoruid2":2903254}},{"author":"王练","id":78702,"title":"设计师们也要失业？Adobe 发布 Sensei AI 平台","body":"没错，Adobe 作为全球知名的数字媒体编辑软件供应商，也加入了人工智能的...","authorid":2896879,"pubDate":"2016-11-04 07:36:30","url":"","commentCount":6,"newstype":{"eventurl":"","type":0,"authoruid2":2896879}},{"author":"王练","id":78701,"title":"FireFox 回温，微软浏览器再失4000万用户","body":"Windows 10周年更新为微软Edge浏览器带来诸多改进，如支持流行的扩展，像...","authorid":2896879,"pubDate":"2016-11-04 07:32:01","url":"","commentCount":25,"newstype":{"eventurl":"","type":0,"authoruid2":2896879}}]}
     */


    @XStreamAlias("catalog")
    public int catalog;
    @XStreamAlias("pagesize")
    public int pagesize;
    @XStreamAlias("newsCount")
    public int newsCount;
    @XStreamAlias("newslist")
    public NewslistBean newslist;


    public static class NewslistBean {
        /**
         * author : 淡漠悠然
         * id : 78722
         * title : Spring Data Commons 1.12.5 发布
         * body : Spring Data Commons 1.12.5 发布了，Spring Data 项目的目的是为了简化...
         * authorid : 2305107
         * pubDate : 2016-11-04 18:29:43
         * url :
         * commentCount : 0
         * newstype : {"eventurl":"","type":0,"authoruid2":2305107}
         */

        @XStreamAlias("news")
        public List<NewsBean> news;


        public static class NewsBean {
            @XStreamAlias("author")
            public String author;
            @XStreamAlias("id")
            public int id;
            @XStreamAlias("title")
            public String title;
            @XStreamAlias("body")
            public String body;
            @XStreamAlias("authorid")
            public int authorid;
            @XStreamAlias("pubDate")
            public String pubDate;
            @XStreamAlias("url")
            public String url;
            @XStreamAlias("commentCount")
            public int commentCount;
            /**
             * eventurl :
             * type : 0
             * authoruid2 : 2305107
             */

            @XStreamAlias("newstype")
            public NewstypeBean newstype;


            public static class NewstypeBean {
                @XStreamAlias("eventurl")
                public String eventurl;
                @XStreamAlias("type")
                public int type;
                @XStreamAlias("authoruid2")
                public int authoruid2;
            }
        }
    }

}
