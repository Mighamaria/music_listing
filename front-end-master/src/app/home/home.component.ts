import { Component, OnInit } from '@angular/core';

interface Song {
  imageUrl: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  newReleasedSongs: Song[] = [
    {
      imageUrl: 'assets/slideshow/9.png'
    },
    {
      imageUrl: 'assets/slideshow/11.png'
    },
    {
      imageUrl: 'assets/slideshow/10.png'
    },
    {
      imageUrl: 'assets/slideshow/12.png'
    },
    {
      imageUrl: 'assets/slideshow/13.png'
    },
    {
      imageUrl: 'assets/slideshow/14.png'
    },
  ];

  slideIndex = 0;
  carouselInterval: any;

  ngOnInit() {
    this.startCarousel();
  }

  startCarousel() {
    this.carouselInterval = setInterval(() => {
      this.nextSlide();
    }, 3000);
  }

  goToSlide(index: number) {
    this.slideIndex = index;
    clearInterval(this.carouselInterval);
    this.startCarousel();
  }

  previousSlide() {
    this.slideIndex = this.slideIndex === 0 ? this.newReleasedSongs.length - 1 : this.slideIndex - 1;
    clearInterval(this.carouselInterval);
    this.startCarousel();
  }

  nextSlide() {
    this.slideIndex = this.slideIndex === this.newReleasedSongs.length - 1 ? 0 : this.slideIndex + 1;
    clearInterval(this.carouselInterval);
    this.startCarousel();
  }
}
