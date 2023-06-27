import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  songs: any[] = [];
  filteredSongs: any[] = [];
  selectedSong: any = null;
  showSongForm: boolean = false;
  searchText: string = '';
  

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchSongs()
  }

  fetchSongs() {
    this.http.get('http://localhost:8086/1.0/admin/viewAllMusics').subscribe(
      (response: any) => {
        this.songs = response;
        this.filteredSongs = [...this.songs]; // Initialize filtered songs with all songs
      },
      (error: any) => {
        console.error('Error fetching songs:', error);
      }
    );
  }

  updateSong() {
    if (this.selectedSong) {
      this.http.put(`http://localhost:8086/1.0/admin/updateAMusic/${this.selectedSong.musicId}`, this.selectedSong).subscribe(
        (response: any) => {
          console.log('Song updated:', response);
          this.selectedSong = null;
          this.showSongForm = false;
          this.fetchSongs(); // Refresh the song list
        },
        (error: any) => {
          console.error('Error updating song:', error);
        }
      );
    }
  }

  deleteSong(musicId: number) {
    if (musicId) {
      this.http.delete(`http://localhost:8086/1.0/admin/deleteAMusic/${musicId}`).subscribe(
        (response: any) => {
          console.log('Song deleted:', response);
          this.songs = this.songs.filter((s) => s.musicId !== musicId); // Update songs list after deletion
          this.filteredSongs = [...this.songs]; // Update filtered songs after deletion
          this.selectedSong = null;
          this.showSongForm = false;
        },
        (error: any) => {
          console.error('Error deleting song:', error);
        }
      );
    }
  }

  showForm(song: any) {
    this.selectedSong = song ? { ...song } : {};
    this.showSongForm = true;

  }

  cancelForm() {
    this.selectedSong = null;
    this.showSongForm = false;
  }


 


  saveSong() {
    if (this.selectedSong.musicId) {
      // Update existing song
      this.updateSong();
    } else {
      // Create new song
      this.http.post('http://localhost:8086/1.0/admin/addMusic', this.selectedSong,{headers: new HttpHeaders({'Access-Control-Allow-Origin' : '*'})}).subscribe(
        (response: any) => {
          console.log('Song created:', response);
          this.selectedSong.musicId = response.musicId;
          this.songs.push({ ...this.selectedSong });
          this.filteredSongs = [...this.songs]; // Update filtered songs after creation
          this.selectedSong = null;
          this.showSongForm = false;
        },
        (error: any) => {
          console.error('Error creating song:', error);
        }
      );
    }
  }

  handleSearch() {
    if (this.searchText) {
      // Filter songs based on musicId
      const searchId = parseInt(this.searchText, 10);
      this.filteredSongs = this.songs.filter((song) => song.musicId === searchId);
    } else {
      // If search text is empty, display all songs
      this.filteredSongs = [...this.songs];
    }
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    // You can now access the selected file using the 'file' variable and perform any necessary operations.
    // For example, you can upload the file to a server or process it locally.
    console.log(file);
  }
  

}
