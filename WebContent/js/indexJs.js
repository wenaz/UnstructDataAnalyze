/**
 * Created by lenovo on 2015/9/15.
 */
$(function () {
   /* $.jstree.defaults.core.themes.variant = "large";*/

    $('#jstree')
        // listen for event
        .on('changed.jstree', function (e, data) {
            var i, j, r = [];
            for(i = 0, j = data.selected.length; i < j; i++) {
                r.push(data.instance.get_node(data.selected[i]).text);
            }
            $('#event_result').html('Selected: ' + r.join(', '));
        })
        // create the instance
        .jstree({
        "core":{
            /*"themes":{
                "variant" : "large"
            },
            "multiple" : false,
             */
           /* 'data' : {
                'url' : "/hello",
                'data' : function (node) {
                    return { 'id' : node.id };
                },
                dataType:"json"
            }*/
           /* 'data' : [
                'Simple root node',
                {
                    'text' : 'Root node 2',
                    'state' : {
                        'opened' : true,
                        'selected' : true
                    },
                    'children' : [
                        { 'text' : 'Child 1' },
                        'Child 2'
                    ]
                }
            ]*/
        },
        "check_callback":true,
        "checkbox" : {
            "keep_selected_style" : false
        },
        "plugins" : [ "contextmenu", "dnd", "search", "state", "types" ]
    });


    var to = false;
    $('#jstree_search').keyup(function () {
        if(to) { clearTimeout(to); }
        to = setTimeout(function () {
            var v = $('#jstree_search').val();
            $('#jstree').jstree(true).search(v);
        }, 250);
    });

    $('#jstree').on("changed.jstree", function (e, data) {
        console.log(data.selected);
    });

    $('button').on('click', function () {
        $('#jstree').jstree(true).select_node('child_node_1');
      /*  $('#jstree').jstree('select_node', 'child_node_1');
        $.jstree.reference('#jstree').select_node('child_node_1');*/
    });

    initDirs();

});

function initDirs(){
    var req=new XMLHttpRequest();
    req.overrideMimeType("application/json");
    req.open("GET","hdfsFile!createTree",true);
    req.onreadystatechange=function receive(){
      if(req.readyState==4){
          console.log(req.responseText.trim());
          var respData=JSON.parse(req.responseText.trim());
          /*createTree(respData);*/
          $('#jstree').jstree(true).settings.core.data = respData;
          $('#jstree').jstree(true).refresh();
      }
    };
    req.send();
}

function createTree(jsonData){
    console.log(jsonData);
    $('#jstree').jstree({
        'core':{
            'data':jsonData
        }
    });
}