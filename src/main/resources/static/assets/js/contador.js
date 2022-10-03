function count(){
    var counter = { var: 0 };
    TweenMax.to(counter, 3, {
      var: 583, 
      onUpdate: function () {
        var number = Math.ceil(counter.var);
        $('.counter').html(number);
        if(number === counter.var){ counter.kill(); }
      },
      onComplete: function(){
        count();
      },    
      ease:Circ.easeOut
      
      
    });
  }
  
  count();
 
  function years(){
    var counter = { var: 0 };
    TweenMax.to(counter, 3, {
      var: 3, 
      onUpdate: function () {
        var number = Math.ceil(counter.var);
        $('.years').html(number);
        if(number === counter.var){ years.kill(); }
      },
      onComplete: function(){
       years();
      },    
      ease:Circ.easeOut
    });
  }
  
  years();
 
  function people(){
    var counter = { var: 0 };
    TweenMax.to(counter, 3, {
      var: 643, 
      onUpdate: function () {
        var number = Math.ceil(counter.var);
        $('.people').html(number);
        if(number === counter.var){ people.kill(); }
      },
      onComplete: function(){
       people();
      },    
      ease:Circ.easeOut
    });
  }
  
  people();
 