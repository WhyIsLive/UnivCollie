
      !(function(){
        var uniAppViewReadyCallback = function(){
          setCssToHead([".",[1],"content { text-align: center; height:",[0,700],"; width:100% }\n.",[1],"map1{ height: 100%; width: 100%; margin-top: ",[0,200],"; }\n.",[1],"title { font-size: ",[0,36],"; color: #8f8f94; }\n",],undefined,{path:"./pages/index/index.wxss"})();
document.dispatchEvent(new CustomEvent("generateFuncReady", { detail: { generateFunc: $gwx('./pages/index/index.wxml') } }));
        }
        if(window.__uniAppViewReady__){
          uniAppViewReadyCallback()
        }else{
          document.addEventListener('uniAppViewReady',uniAppViewReadyCallback)
        }
      })();
      