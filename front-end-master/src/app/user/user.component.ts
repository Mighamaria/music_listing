import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

interface Song {
  title: string;
  artist: string;
  views: string;
  imageUrl: string;
  audioUrl: string;
}

interface Songs {
  imageUrl: string;
}

enum SearchCategory {
  musicName,
  artistName,
  songLanguage,
  genre,

}

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  songs: Song[] = [
    {
      title: 'Song 1',
      artist: 'Artist 1',
      views: '7.6',
      imageUrl: 'assets/slideshow/14.png',
      audioUrl: 'path/to/song1-audio.mp3'
    },
    {
      title: 'Song 2',
      artist: 'Artist 2',
      views: '8.6',
      imageUrl: 'assets/slideshow/13.png',
      audioUrl: 'path/to/song2-audio.mp3'
    },
    {
      title: 'Song 13',
      artist: 'Artist 3',
      views: '5.6',
      imageUrl: 'assets/slideshow/8.png',
      audioUrl: 'path/to/song1-audio.mp3'
    },
    {
      title: 'Song 4',
      artist: 'Artist 4',
      views: '6.6',
      imageUrl: 'assets/slideshow/10.png',
      audioUrl: 'path/to/song1-audio.mp3'
    },
    // Add more songs here...
  ];

  selectedCategory: SearchCategory = SearchCategory.musicName;

  showModal: boolean = false;
  selectedRating: number = 0;
  ratings: number[] = [];
  filteredSongs: any[] = [];
  selectedSong: any = null;
  showSongForm: boolean = false;
  searchText: string = '';
  searchQuery: string = '';
  showSearchModal: boolean = false;
  maxRating: number = 5;
  selectCategory: any = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchSongs();
    this.startCarousel();
  }

  fetchSongs() {
    this.filteredSongs = [...this.songs];
  }

  showForm() {
    this.selectedSong = {};
    this.showSongForm = true;
  }

  cancelForm() {
    this.selectedSong = null;
    this.showSongForm = false;
  }

  newReleasedSongs: Songs[] = [
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

  newReleasedSongs1: Songs[] = [
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

  newReleasedSongs2: Songs[] = [
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

  openSearchModal() {
    this.showSearchModal = true;
  }

  closeSearchModal() {
    this.showSearchModal = false;
  }

  closeSongForm(): void {
    this.showSongForm = false;
  }

  saveSong() {
    console.log('Song saved:', this.selectedSong);

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    this.http.post('http://localhost:9095/api/1.0/users/addMusic', this.selectedSong, { headers }).subscribe(
      (response) => {
        console.log('Song saved successfully:', response);
        this.selectedSong = null;
        this.showSongForm = false;
        this.fetchSongs();
      },
      (error) => {
        console.error('Error saving song:', error);
      }
    );
  }

  getRatingStars(totalStars: number): number[] {
    return Array(totalStars).fill(0).map((_, index) => index + 1);
  }

  isSelected(star: number): boolean {
    return star <= this.selectedRating;
  }

  selectRating(rating: number): void {
    this.selectedRating = rating;
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    console.log(file);
  }

  openModal(song: Song) {
    this.selectedSong = song;
    this.showModal = true;
  }

  closeModal() {
    this.selectedSong = undefined;
    this.showModal = false;
  }

  addRatingToMusic(musicId: number, userId: number, rating: number) {
    const ratingDto = { rating: rating };
    this.http.post<any>(`http://localhost:9095/api/1.0/users/add/rating/music/${musicId}/${userId}`, ratingDto).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getMusicOverallRating(musicId: number) {
    this.http.get<number>(`http://localhost:9095/api/1.0/users/rating/overall/${musicId}`).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  searchByCategory(category: SearchCategory) {
    switch (category) {
      case SearchCategory.musicName:
        this.http.get<any[]>(`http://localhost:9095/api/1.0/users/search/music/name/${this.searchQuery}`).subscribe(
          (response) => {
            this.songs = response;
          },
          (error) => {
            console.error(error);
          }
        );
        break;
      case SearchCategory.artistName:
        this.http.get<any[]>(`http://localhost:9095/api/1.0/users/search/music/artistName/${this.searchQuery}`).subscribe(
          (response) => {
            this.songs = response;
          },
          (error) => {
            console.error(error);
          }
        );
        break;
      case SearchCategory.songLanguage:
        this.http.get<any[]>(`http://localhost:9095/api/1.0/users/search/music/songLanguage/${this.searchQuery}`).subscribe(
          (response) => {
            this.songs = response;
          },
          (error) => {
            console.error(error);
          }
        );
        break;
      case SearchCategory.genre:
        this.http.get<any[]>(`http://localhost:9095/api/1.0/users/search/music/musicGenre/${this.searchQuery}`).subscribe(
          (response) => {
            this.songs = response;
          },
          (error) => {
            console.error(error);
          }
        );
        break;
      default:
        console.log("Category not found");
        break;
    }
  }


}
