/**
 * Created by yougen.zhuangyg on 2014/7/23.
 */
(function(){
   var Slide = function(){
       this.initialize.apply(this, arguments);
   }

   Slide.prototype = {
       constructor: Slide,
       initialize: function(options){
           this.options = options || {};
           this.prev =  $(options.prev || "#J-prevTheme");
           this.next = $(options.next || "#J-nextTheme");
           this.slideContainer = $(options.slideContainer || "#J-slideContainer");
           this.slideTarget = $(options.slideTarget || "#J-slideTarget");
           this.slides = options.slides || [];
           this.targets = options.targets || [];
           this.currentSlide = 0;
           this.installListeners();
       },
       installListeners:function(){
           this.prev.on("click", $.proxy(this.navSlide, this));
           this.next.on("click", $.proxy(this.navSlide, this));
       },
       slide:function(slide){
           this.slideContainer.css("background-image",'url(' + slide + ')');
           this.slideTarget.attr("href", this.targets[this.currentSlide]);
       },
       navSlide: function(e){
          e.preventDefault();
          var deep = $(e.currentTarget)[0] === this.prev[0] ? -1: 1;
          this.currentSlide += deep;
          this.currentSlide = this.currentSlide % 3;
          if(this.currentSlide <0){
              this.currentSlide += this.slides.length;
          }
          this.slide(this.slides[this.currentSlide]);
       }
   }

   new Slide({
       slides: [
                   "http://mimg.127.net/index/163/themes/140717_qnyh_cnt.jpg",
                   "http://mimg.127.net/index/163/themes/140624_1ydb_cnt.jpg",
                   "http://mimg.127.net/index/163/themes/140718_zhangly_cnt.jpg"
       ],
       targets: [
                 "http://www.baidu.com",
                 "http://www.sina.com",
                 "http://www.163.com"
       ]
   });
})();