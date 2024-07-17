

document.addEventListener("DOMContentLoaded", function () {

    // 슬라이드 효과를 적용할 요소
    const boxes = document.querySelectorAll('.box');

    boxes.forEach(box => {
        box.addEventListener('mouseover', function () {
            this.style.transition = "transform 0.5s";
            this.style.transform = "scale(1.05)";
        });

        box.addEventListener('mouseout', function () {
            this.style.transition = "transform 0.5s";
            this.style.transform = "scale(1)";
        });
    });

});

// 스크롤 위치가 일정 이상 내려가면 버튼 보이기
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  var scrollToTopBtn = document.getElementById("scrollToTopBtn");
  
  if (document.documentElement.scrollTop > window.innerHeight * 0.9) {
    scrollToTopBtn.style.display = "block";
  } else {
    scrollToTopBtn.style.display = "none";
  }
}

// 맨 위로 부드럽게 올라가는 기능 구현
function scrollToTop() {
  var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
  
  if (scrollTop > 0) {
    window.requestAnimationFrame(scrollToTop);
    window.scrollTo(0, scrollTop - scrollTop / 8); // 부드러운 스크롤 조정 (8은 조정 강도를 나타냄)
  }
}

