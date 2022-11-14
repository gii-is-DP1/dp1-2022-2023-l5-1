/*function createFields() {
    $('.field').remove();
    var container = $('#container');
    for(var i = 0; i < +$('input:text').val(); i++) {
        $('<div/>', {
            'class': 'field',
            'text': i + 1
        }).appendTo(container);
    }
}

function distributeFields() {
    var radius = 200;
    var fields = $('.field'), container = $('#container'),
        width = container.width(), height = container.height(),
        angle = 0, step = (2*Math.PI) / fields.length;
    fields.each(function() {
        var x = Math.round(width/2 + radius * Math.cos(angle) - $(this).width()/2);
        var y = Math.round(height/2 + radius * Math.sin(angle) - $(this).height()/2);
        if(window.console) {
            console.log($(this).text(), x, y);
        }
        $(this).css({
            left: x + 'px',
            top: y + 'px'
        });
        angle += step;
    });
}

$('input').change(function() {
    createFields();
    distributeFields();
});*/
/*
HTML

Number of fields: <input type="text" value="4" />
<div id="container">
    <div id="center"></div>
    <div id="crosshair-x"></div>
    <div id="crosshair-y"></div>
</div>

CSS 

body { padding: 2em; }
#container { width: 600px; height: 600px; border: 1px solid #000; position: relative; }
#center { width: 10px; height: 10px; position: absolute; left: 295px; top: 295px; background: #000; }
.field { width: 20px; height: 20px; position: absolute; background: #f00; }
#crosshair-x { width: 600px; height: 1px; background: #000; position: absolute; left: 0; top: 300px; }
#crosshair-y { width: 1px; height: 600px; background: #000; position: absolute; left: 300px; top: 0; }


*/
createFields();
distributeFields();