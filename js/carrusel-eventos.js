const carrusel = document.querySelector('.carrusel');
const slides = document.querySelectorAll('.slide');
const puntos = document.querySelectorAll('.punto');
const btnNext = document.querySelector('.carrusel-btn.next');
const btnPrev = document.querySelector('.carrusel-btn.prev');

let index = 0;

function actualizarCarrusel() {
  carrusel.style.transform = `translateX(-${index * 100}%)`;
  puntos.forEach((p, i) => p.classList.toggle('activo', i === index));
}

btnNext.addEventListener('click', () => {
  index = (index + 1) % slides.length;
  actualizarCarrusel();
  reiniciarAutoPlay();
});

btnPrev.addEventListener('click', () => {
  index = (index - 1 + slides.length) % slides.length;
  actualizarCarrusel();
  reiniciarAutoPlay();
});

puntos.forEach((p, i) => {
  p.addEventListener('click', () => {
    index = i;
    actualizarCarrusel();
    reiniciarAutoPlay();
  });
});

// Movimiento automático
let autoPlay = setInterval(() => {
  index = (index + 1) % slides.length;
  actualizarCarrusel();
}, 5000);

// Reiniciar autoplay si el usuario interactúa
function reiniciarAutoPlay() {
  clearInterval(autoPlay);
  autoPlay = setInterval(() => {
    index = (index + 1) % slides.length;
    actualizarCarrusel();
  }, 5000);
}

// Pausar autoplay al hacer hover
carrusel.addEventListener('mouseenter', () => {
  clearInterval(autoPlay);
});

// Reanudar autoplay al salir del hover
carrusel.addEventListener('mouseleave', () => {
  autoPlay = setInterval(() => {
    index = (index + 1) % slides.length;
    actualizarCarrusel();
  }, 5000);
});

actualizarCarrusel();