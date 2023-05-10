function clickCarousel(){
    let slide1 = document.getElementById('slide1');
    let slide2 = document.getElementById('slide2');
    let slide3 = document.getElementById('slide3');
    let point1 = document.getElementById('point1');
    let point2 = document.getElementById('point2');
    let point3 = document.getElementById('point3');
    let btn_left = document.getElementById('btn_left');
    let btn_right = document.getElementById('btn_right');
    
    btn_left.onclick = function() {
        if (point1.classList.contains('current_point'))
        {
            changeSlideLeft(slide1, slide3, slide2, point1, point3);
        }
        else if (point2.classList.contains('current_point')){
            changeSlideLeft(slide2, slide1, slide3, point2, point1);
        }
        else{
            changeSlideLeft(slide3, slide2, slide1, point3, point2);
        };
    };

    btn_right.onclick = function() {
        if (point1.classList.contains('current_point'))
        {
            changeSlideRight(slide1, slide2, slide3, point1, point2);
        }
        else if (point2.classList.contains('current_point')){
            changeSlideRight(slide2, slide3, slide1, point2, point3);
        }
        else{
            changeSlideRight(slide3, slide1, slide2, point3, point1);
        };
    };
}

function changeSlideLeft(cur_slide, new_slide, old_slide, cur_point, new_point)
{
    new_slide.style.zIndex = '20';
    cur_slide.style.zIndex = '10';
    old_slide.style.zIndex = '-10';
    cur_point.classList.toggle('current_point');
    new_point.classList.toggle('current_point');
    cur_slide.style.left = '100%';
    new_slide.style.left = '0';
    old_slide.style.left = '-100%';
    
};

function changeSlideRight(cur_slide, new_slide, old_slide, cur_point, new_point)
{
    new_slide.style.zIndex = '20';
    cur_slide.style.zIndex = '10';
    old_slide.style.zIndex = "-10";
    cur_point.classList.toggle('current_point');
    new_point.classList.toggle('current_point');
    cur_slide.style.left = '-100%';
    new_slide.style.left = '0';
    old_slide.style.left = '100%';
};

clickCarousel();
