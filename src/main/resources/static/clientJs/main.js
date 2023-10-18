$(document).ready(function() {

    $(".minus-adult,.add-adult").click(function(e) {
        e.preventDefault();
        if ($(this).hasClass("add-adult")) {

            var val = parseInt($(this).parents("li").find(".adult-total").text()) + 1;
            $(".adult-total").text(val);

        } else {

            var val = parseInt($(this).parents("li").find(".adult-total").text()) - 1;
            if (val >= 0) {
                $(".adult-total").text(val);
            }
        }
    });

    $(".minus-child,.add-child").click(function(e) {
        e.preventDefault();
        if ($(this).hasClass("add-child")) {

            $('.children-span').css({ visibility: 'visible' });

            var val = parseInt($(this).parents("li").find(".child-total").text()) + 1;
            $(".child-total").text(val);

        } else {

            var val = parseInt($(this).parents("li").find(".child-total").text()) - 1;
            if (val >= 0) {
                $(".child-total").text(val);
            } else {
                $(".children-span").css({ visibility: 'hidden' });
            }

        }
    });



    if ($(".match-height").length > 0) {
        $('.match-height').matchHeight();
    }

    if ($(".dropdown-group").length > 0) {
        $('.dropdown-menu li').on('click', function() {
            var getValue = $(this).text();
            $('.dropdown-select').text(getValue);
        });
    }

    if ($(".destination-carousel").length > 0) {

        $('.destination-carousel').owlCarousel({
            stagePadding: 50,
            items: 2,
            loop: false,
            nav: true,
            dots: false,
            margin: 5,
            navText: ["<i class='icons icon-keyboard_arrow_left'></i>", "<i class='icons icon-keyboard_arrow_right'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                400: {
                    items: 2
                },
                600: {
                    items: 3
                },
                700: {
                    items: 4
                },
                1000: {
                    items: 6
                }
            },
            onTranslated: callBack
        });

        function callBack() {
            if ($('.owl-carousel .owl-item').last().hasClass('active')) {
                $('.owl-next').hide();
                $('.owl-prev').show();
                console.log('true');
            } else if ($('.owl-carousel .owl-item').first().hasClass('active')) {
                $('.owl-prev').hide();
                $('.owl-next').show();
                console.log('false');
            }
        }

        $('.destination-carousel .owl-prev').hide();
    }

    if ($(".listing-carousel").length > 0) {

        $('.listing-carousel').owlCarousel({
            items: 1,
            loop: true,
            center: true,
            margin: 10,
            dots: false,
            URLhashListener: true,
            autoplayHoverPause: true,
            startPosition: 'URLHash'
        });

    }

    if ($(".guest-carousel").length > 0) {
        $('.guest-carousel').owlCarousel({
            stagePadding: 50,
            loop: true,
            margin: 10,
            nav: true,
            navText: ["<i class='icons icon-keyboard_arrow_left'></i>", "<i class='icons icon-keyboard_arrow_right'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                1000: {
                    items: 2
                }
            }
        });
    }

    if ($(".site-container").length > 0) {
        // show password
        $("[data-trigger]").on("click", function(e) {
            e.preventDefault();
            e.stopPropagation();
            var offcanvas_id = $(this).attr("data-trigger");
            $(offcanvas_id).toggleClass("show");
            $("body").toggleClass("offcanvas-active");
            $(".screen-overlay").toggleClass("show");
        });

        $(".btn-close, .screen-overlay").click(function(e) {
            $(".screen-overlay").removeClass("show");
            $(".mobile-offcanvas").removeClass("show");
            $("body").removeClass("offcanvas-active");
        });
    }

});


$(window).scroll(function() {
    if ($(window).scrollTop() >= 450) {
        $('.tob-bar').addClass('fixed-header');
    } else {
        $('.tob-bar').removeClass('fixed-header');
    }
});

var position = $(window).scrollTop();

// should start at 0

$(window).scroll(function() {
    var scroll = $(window).scrollTop();
    if (scroll < position) {
        $('.tob-bar').removeClass('fixed-header');
    }
    position = scroll;
});

$(document).on('click.bs.dropdown.data-api', '.dropdown.keep-inside-clicks-open', function(e) {
    e.stopPropagation();
});