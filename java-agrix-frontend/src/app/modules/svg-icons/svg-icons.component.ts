import { Component, Input, OnChanges } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-svg-icons',
  template: `<div [innerHtml]="svgContent"></div>`,
})
export class SvgIconsComponent implements OnChanges {
  @Input() iconName!: string;
  @Input() strokeColor: string = 'currentColor';
  @Input() fillColor: string = 'currentColor';
  @Input() size: string = '24px';
  svgContent: SafeHtml = ''; // Modificamos para SafeHtml

  constructor(private sanitizer: DomSanitizer) {} // Injeta o DomSanitizer

  ngOnChanges() {
    fetch(`assets/images/${this.iconName}.svg`)
      .then((response) => response.text())
      .then((svg) => {
        const sanitizedSvg = this.sanitizer.bypassSecurityTrustHtml(
          svg
            .replace(/stroke="[^"]*"/g, `stroke="${this.strokeColor}"`)
            .replace(/width="[^"]*"/, `width="${this.size}"`)
            .replace(/height="[^"]*"/, `height="${this.size}"`),
        );
        this.svgContent = sanitizedSvg;
      });
  }
}
