import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILocation } from '../location.model';
import { LocationService } from '../service/location.service';
import { LocationDeleteDialogComponent } from '../delete/location-delete-dialog.component';

@Component({
  selector: 'jhi-location',
  templateUrl: './location.component.html',
})
export class LocationComponent implements OnInit {
  locations?: ILocation[];
  isLoading = false;

  constructor(protected locationService: LocationService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.locationService.query().subscribe({
      next: (res: HttpResponse<ILocation[]>) => {
        this.isLoading = false;
        this.locations = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: ILocation): number {
    return item.id!;
  }

  delete(location: ILocation): void {
    const modalRef = this.modalService.open(LocationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.location = location;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
